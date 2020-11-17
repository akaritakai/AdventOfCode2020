FROM gradle:6.7-jdk15

WORKDIR "/opt/aoc"

COPY . .

ENTRYPOINT ["gradle", "run"]