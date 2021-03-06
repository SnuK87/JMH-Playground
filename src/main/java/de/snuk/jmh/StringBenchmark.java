package de.snuk.jmh;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

public class StringBenchmark {

	@State(Scope.Thread)
	public static class MyState {
		String a = "Hello";
		String b = "World";
		String c = "!";
	}

	@Benchmark
	@BenchmarkMode(Mode.Throughput)
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void testPlusOperator(final MyState state, final Blackhole blackhole) {
		final String result = state.a + state.b + state.c;
		blackhole.consume(result);
	}

	@Benchmark
	@BenchmarkMode(Mode.Throughput)
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void testConcat(final MyState state, final Blackhole blackhole) {
		final String result = state.a.concat(state.b).concat(state.c);
		blackhole.consume(result);
	}

}
