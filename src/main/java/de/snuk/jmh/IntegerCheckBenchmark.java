package de.snuk.jmh;

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
			if (!Character.isDigit(arg.charAt(i))) {
				return false;
			}
		}

		return true;
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
