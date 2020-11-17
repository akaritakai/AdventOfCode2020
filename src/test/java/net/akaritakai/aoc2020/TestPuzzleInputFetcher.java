package net.akaritakai.aoc2020;

import com.google.common.net.HttpHeaders;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.apache.commons.lang3.RandomStringUtils;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;

import static org.mockito.Mockito.*;

public class TestPuzzleInputFetcher {
    @Test
    public void testGetPuzzleInputInLocalStore() throws Exception {
        var fetcher = Mockito.spy(new PuzzleInputFetcher());
        for (int day = 1; day <= 25; day++) {
            var puzzleInput = RandomStringUtils.randomAscii(65_535);
            doReturn(puzzleInput).when(fetcher).fetchLocalPuzzleInput(day);
            doThrow(new IOException("Expected")).when(fetcher).fetchRemotePuzzleInput(day);
            doThrow(new IOException("Expected")).when(fetcher).storePuzzleInputLocally(day, puzzleInput);
            Assert.assertEquals(fetcher.getPuzzleInput(day), puzzleInput);
            verify(fetcher, times(1)).fetchLocalPuzzleInput(day);
            verify(fetcher, never()).fetchRemotePuzzleInput(day);
            verify(fetcher, never()).storePuzzleInputLocally(day, puzzleInput);
        }
    }

    @Test
    public void testGetPuzzleInputInRemoteStore() throws Exception {
        var fetcher = Mockito.spy(new PuzzleInputFetcher());
        for (int day = 1; day <= 25; day++) {
            var puzzleInput = RandomStringUtils.randomAscii(65_535);
            doThrow(new IOException("Expected")).when(fetcher).fetchLocalPuzzleInput(day);
            doReturn(puzzleInput).when(fetcher).fetchRemotePuzzleInput(day);
            doNothing().when(fetcher).storePuzzleInputLocally(day, puzzleInput);
            Assert.assertEquals(fetcher.getPuzzleInput(day), puzzleInput);
            verify(fetcher, times(1)).fetchLocalPuzzleInput(day);
            verify(fetcher, times(1)).fetchRemotePuzzleInput(day);
            verify(fetcher, times(1)).storePuzzleInputLocally(day, puzzleInput);
        }
    }

    @Test
    public void testGetPuzzleInputIssueWhenStoringPuzzleDoesNotThrow() throws Exception {
        var fetcher = Mockito.spy(new PuzzleInputFetcher());
        for (int day = 1; day <= 25; day++) {
            var puzzleInput = RandomStringUtils.randomAscii(65_535);
            doThrow(new IOException("Expected")).when(fetcher).fetchLocalPuzzleInput(day);
            doReturn(puzzleInput).when(fetcher).fetchRemotePuzzleInput(day);
            doThrow(new IOException("Expected")).when(fetcher).storePuzzleInputLocally(day, puzzleInput);
            Assert.assertEquals(fetcher.getPuzzleInput(day), puzzleInput);
            verify(fetcher, times(1)).fetchLocalPuzzleInput(day);
            verify(fetcher, times(1)).fetchRemotePuzzleInput(day);
            verify(fetcher, times(1)).storePuzzleInputLocally(day, puzzleInput);
        }
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void testGetPuzzleInputThrowsWhenAllSourceUnavailable() throws Exception {
        var fetcher = Mockito.spy(new PuzzleInputFetcher());
        doThrow(new IOException("Expected")).when(fetcher).fetchLocalPuzzleInput(1);
        doThrow(new IOException("Expected")).when(fetcher).fetchRemotePuzzleInput(1);
        fetcher.getPuzzleInput(1);
    }

    @Test
    public void testGetPuzzleInputInCache() throws Exception {
        var fetcher = Mockito.spy(new PuzzleInputFetcher());
        for (int day = 1; day <= 25; day++) {
            var puzzleInput = RandomStringUtils.randomAscii(65_535);
            doReturn(puzzleInput).when(fetcher).fetchLocalPuzzleInput(day);
            Assert.assertEquals(fetcher.getPuzzleInput(day), puzzleInput);
            Assert.assertEquals(fetcher.getPuzzleInput(day), puzzleInput);
            verify(fetcher, times(1)).fetchLocalPuzzleInput(day);
        }
    }

    @Test
    public void testGetSessionToken() throws Exception {
        var puzzleStorePath = Files.createTempDirectory("puzzleStore");
        var sessionTokenPath = Files.createTempFile("session", "token");
        var fetcher = new PuzzleInputFetcher(puzzleStorePath, sessionTokenPath);

        var sessionToken = RandomStringUtils.randomAlphanumeric(24);
        Files.writeString(sessionTokenPath, sessionToken);

        Assert.assertEquals(fetcher.getSessionToken(), sessionToken);
    }

    @Test
    public void testGetRemotePuzzleInputUrl() {
        var fetcher = new PuzzleInputFetcher();
        for (var day = 1; day <= 25; day++) {
            Assert.assertEquals(fetcher.getRemotePuzzleInputUrl(day),
                    HttpUrl.get("https://adventofcode.com/2020/day/" + day + "/input"));
        }
    }

    @Test
    public void testFetchRemotePuzzleInput() throws Exception {
        var fetcher = Mockito.spy(new PuzzleInputFetcher());
        var sessionToken = RandomStringUtils.randomAlphanumeric(24);
        doReturn(sessionToken).when(fetcher).getSessionToken();
        for (var day = 1; day <= 25; day++) {
            try (var server = new MockWebServer()) {
                var puzzleInput = RandomStringUtils.randomAscii(65_535);
                server.enqueue(new MockResponse().setBody(puzzleInput));
                server.start();
                var url = server.url("/2020/day/" + day + "/input");
                doReturn(url).when(fetcher).getRemotePuzzleInputUrl(day);
                Assert.assertEquals(fetcher.fetchRemotePuzzleInput(day), puzzleInput);
                var request = server.takeRequest();
                Assert.assertEquals(request.getHeader(HttpHeaders.COOKIE), "session=" + sessionToken);
            }
        }
    }

    @Test(expectedExceptions = {IOException.class})
    public void testFetchRemotePuzzleInputFailsIfMissingSessionToken() throws Exception {
        var fetcher = Mockito.spy(new PuzzleInputFetcher());
        doThrow(new IOException("Expected")).when(fetcher).getSessionToken();
        fetcher.fetchRemotePuzzleInput(1);
    }

    @Test(expectedExceptions = {IOException.class})
    public void testFetchRemotePuzzleInputFailsIfSessionTokenWrong() throws Exception {
        var fetcher = Mockito.spy(new PuzzleInputFetcher());
        var sessionToken = RandomStringUtils.randomAlphanumeric(24);
        doReturn(sessionToken).when(fetcher).getSessionToken();
        try (var server = new MockWebServer()) {
            server.enqueue(new MockResponse()
                    .setResponseCode(400)
                    .setBody("Puzzle inputs differ by user.  Please log in to get your puzzle input."));
            server.start();
            var url = server.url("/2020/day/1/input");
            doReturn(url).when(fetcher).getRemotePuzzleInputUrl(1);
            fetcher.fetchRemotePuzzleInput(1);
        }
    }

    @Test
    public void testStorePuzzleInputLocally() throws Exception {
        var puzzleStorePath = Files.createTempDirectory("puzzleStore");
        var sessionTokenPath = Files.createTempFile("session", "token");
        var fetcher = new PuzzleInputFetcher(puzzleStorePath, sessionTokenPath);
        for (int day = 1; day <= 25; day++) {
            var puzzleInput = RandomStringUtils.randomAscii(65_535);
            fetcher.storePuzzleInputLocally(day, puzzleInput);
            Assert.assertEquals(Files.readString(puzzleStorePath.resolve(String.valueOf(day))), puzzleInput);
        }
    }

    @Test
    public void testFetchLocalPuzzleInput() throws Exception {
        var puzzleStorePath = Files.createTempDirectory("puzzleStore");
        var sessionTokenPath = Files.createTempFile("session", "token");
        var fetcher = new PuzzleInputFetcher(puzzleStorePath, sessionTokenPath);
        for (int day = 1; day <= 25; day++) {
            var puzzleInput = RandomStringUtils.randomAscii(65_535);
            Files.writeString(puzzleStorePath.resolve(String.valueOf(day)), puzzleInput);
            Assert.assertEquals(fetcher.fetchLocalPuzzleInput(day), puzzleInput);
        }
    }

    @Test(expectedExceptions = {IOException.class})
    public void testFetchLocalPuzzleInputFailsOnMissingFile() throws Exception {
        var puzzleStorePath = Files.createTempDirectory("puzzleStore");
        var sessionTokenPath = Files.createTempFile("session", "token");
        var fetcher = new PuzzleInputFetcher(puzzleStorePath, sessionTokenPath);
        fetcher.fetchLocalPuzzleInput(1);
    }
}
