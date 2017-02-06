package de.snuk.jmh;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class IntegerCheckBenchmark {

	@Param({ "123456", "12c456", "12345f" })
	public String arg;

	@Benchmark
	@BenchmarkMode(Mode.Throughput)
	@OutputTimeUnit(TimeUnit.SECONDS)
	public boolean charCheck() {
		if (arg == null) {
			return false;
		}

		for (int i = 0; i < arg.length(); i++) {
			if (Character.digit(arg.charAt(i), 10) < 0) {
				return false;
			}

			if ((i == 0) && (arg.charAt(i) == '-')) {
				if (arg.length() == 1) {
					return false;
				} else {
					continue;
				}
			}

		}

		return true;
	}

	@Benchmark
	@BenchmarkMode(Mode.Throughput)
	@OutputTimeUnit(TimeUnit.SECONDS)
	public boolean scannerCheck() {
		final Scanner scanner = new Scanner(arg.trim());
		if (!scanner.hasNextInt(10)) {
			return false;
		}

		scanner.nextInt(10);
		return !scanner.hasNext();
	}

	@Benchmark
	@BenchmarkMode(Mode.Throughput)
	@OutputTimeUnit(TimeUnit.SECONDS)
	public boolean trollYourReviewer() {
		if (arg == null) {
			return false;
		}

		try {
			Integer.parseInt(arg);
		} catch (final NumberFormatException e) {
			return false;
		}

		return true;
	}
}
