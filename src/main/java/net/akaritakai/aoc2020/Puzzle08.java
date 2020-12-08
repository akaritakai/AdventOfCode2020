package net.akaritakai.aoc2020;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Day 8 asks us to simulate a simple machine and determine at what state it repeats an instruction or halts.
 *
 * Because the machine cannot repeat any earlier instruction, it cannot take more than O(n) to either repeat an
 * instruction or halt.
 *
 * In part 2, we are asked find the line in the program that can be acceptable mutated to get the program to halt. This
 * search would naively take O(n^2), but it can be done in O(n) because we never to test any instruction previously
 * tested: if the mutation redirects to a previously seen instruction and the remaining code path does not involve the
 * mutated instruction, then we have already tested it; conversely, if the mutation redirects to a previously seen
 * instruction and the remaining code path does involve the mutated instruction, then we have looped and won't halt.
 *
 * A much harder version of this problem is the halting problem, which asks if a given program/input tuple will halt.
 * The halting problem is famously undecidable over the domain of all program/input tuples.
 */
public class Puzzle08 extends AbstractPuzzle {
    public Puzzle08(String puzzleInput) {
        super(puzzleInput);
    }

    @Override
    public int getDay() {
        return 8;
    }

    @Override
    public String solvePart1() {
        var machine = new Machine(instructions(), new HashSet<>());
        var registers = new Registers(0, 0);
        while (machine.seen.add(registers.ip)) {
            registers = machine.execute(registers.ip, registers.acc);
        }
        return String.valueOf(registers.acc);
    }

    @Override
    public String solvePart2() {
        var machine = new Machine(instructions(), new HashSet<>());
        var value = machine.findReturnValue(new Registers(0, 0), 0).orElseThrow();
        return String.valueOf(value);
    }

    private List<Instruction> instructions() {
        return getPuzzleInput().lines().map(Instruction::from).collect(Collectors.toList());
    }

    private record Machine(List<Instruction> instructions, Set<Integer> seen) {
        public Registers execute(int ip, int acc) {
            return execute(ip, acc, instructions.get(ip));
        }

        public Registers execute(int ip, int acc, Instruction instruction) {
            switch (instruction.op) {
                case "acc" -> acc += instruction.value;
                case "jmp" -> ip += instruction.value - 1;
            }
            return new Registers(++ip, acc);
        }

        private Optional<Integer> findReturnValue(Registers registers, int modifications) {
            if (registers.ip >= instructions.size()) {
                return Optional.of(registers.acc);
            }
            if (!seen.add(registers.ip)) {
                return Optional.empty();
            }
            return findReturnValue(execute(registers.ip, registers.acc), modifications).or(() -> {
                var instruction = instructions.get(registers.ip);
                if (instruction.op.equals("nop") && modifications < 1) {
                    instruction = new Instruction("jmp", instruction.value);
                    return findReturnValue(execute(registers.ip, registers.acc, instruction), modifications + 1);
                } else if (instruction.op.equals("jmp") && modifications < 1) {
                    instruction = new Instruction("nop", instruction.value);
                    return findReturnValue(execute(registers.ip, registers.acc, instruction), modifications + 1);
                } else {
                    return Optional.empty();
                }
            });
        }
    }

    private record Instruction(String op, int value) {
        public static Instruction from(String s) {
            return new Instruction(s.split(" ")[0], Integer.parseInt(s.split(" ")[1]));
        }
    }

    private record Registers(int ip, int acc) {
    }
}
