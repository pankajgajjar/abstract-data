package com.cs.utils;

import java.util.Arrays;

public class ArrayUtils {

	public String[] skip(String[] inputs, int skipIndex) {

		inputs = Arrays.copyOfRange(inputs, 1, inputs.length);
		return inputs;

	}
}
