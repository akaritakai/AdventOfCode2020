package net.akaritakai.aoc2020;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.net.HttpHeaders;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PuzzleInputFetcher {
    private static final Logger LOG = LoggerFactory.getLogger(PuzzleInputFetcher.class);

    private final Map<Integer, String> _cache = new ConcurrentHashMap<>();
    private final OkHttpClient _httpClient = new OkHttpClient();
    private final Path _puzzleStorePath;
    private final Path _sessionTokenPath;

    private String _sessionToken;

    public PuzzleInputFetcher() {
        this(Path.of("puzzle"), Path.of("cookie.txt"));
    }

    @VisibleForTesting
    PuzzleInputFetcher(Path puzzleStorePath, Path sessionTokenPath) {
        _puzzleStorePath = puzzleStorePath;
        _sessionTokenPath = sessionTokenPath;
    }

    public String getPuzzleInput(int day) {
        return _cache.computeIfAbsent(day, s -> {
            try {
                try {
                    return fetchLocalPuzzleInput(day);
                } catch (IOException e) {
                    LOG.warn("Unable to fetch puzzle input from local store for day {}", day, e);
                }

                String input;
                try {
                    input = fetchRemotePuzzleInput(day);
                } catch (IOException e) {
                    LOG.error("Unable to fetch puzzle input from remote store for day {}", day, e);
                    throw e;
                }

                try {
                    storePuzzleInputLocally(day, input);
                } catch (IOException e) {
                    LOG.warn("Unable to store puzzle input locally for day {}", day, e);
                }
                return input;
            } catch (IOException e) {
                throw new RuntimeException("Couldn't get puzzle input for day " + day);
            }
        });
    }

    @VisibleForTesting
    String fetchLocalPuzzleInput(int day) throws IOException {
        LOG.info("Fetching puzzle input from disk for day {}", day);
        return Files.readString(_puzzleStorePath.resolve(String.valueOf(day)));
    }

    @VisibleForTesting
    void storePuzzleInputLocally(int day, String puzzleInput) throws IOException {
        LOG.info("Storing puzzle input on disk for day {}", day);
        Files.createDirectories(_puzzleStorePath);
        var path = _puzzleStorePath.resolve(String.valueOf(day));
        Files.writeString(path, puzzleInput);
    }

    @VisibleForTesting
    String fetchRemotePuzzleInput(int day) throws IOException {
        LOG.info("Fetching puzzle input from Advent of Code for day {}", day);
        var request = new Request.Builder()
                .url(getRemotePuzzleInputUrl(day))
                .header(HttpHeaders.COOKIE, "session=" + getSessionToken())
                .get()
                .build();
        try (var response = _httpClient.newCall(request).execute()) {
            if (response.code() != 200) {
                throw new IOException("Request was not successful. Status code = " + response.code());
            }
            var body = response.body();
            if (body == null) {
                throw new IOException("Request body was empty");
            }
            return body.string();
        }
    }

    @VisibleForTesting
    HttpUrl getRemotePuzzleInputUrl(int day) {
        return HttpUrl.get("https://adventofcode.com/2020/day/" + day + "/input");
    }

    @VisibleForTesting
    synchronized String getSessionToken() throws IOException {
        try {
            if (_sessionToken == null) {
                _sessionToken = Files.readString(_sessionTokenPath).trim();
            }
            return _sessionToken;
        } catch (IOException e) {
            throw new IOException("Couldn't get session data from cookie.txt", e);
        }
    }
}
