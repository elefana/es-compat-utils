/*******************************************************************************
 * Copyright 2018 Viridian Software Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.elefana.es.compat.stats;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;

import org.mini2Dx.natives.OsInformation;

public class OsStats {
	private static final OperatingSystemMXBean MX_BEAN = ManagementFactory.getOperatingSystemMXBean();

	private static final Method FREE_PHYSICAL_MEMORY_SIZE;
	private static final Method TOTAL_PHYSICAL_MEMORY_SIZE;
	private static final Method FREE_SWAP_SPACE_SIZE;
	private static final Method TOTAL_SWAP_SPACE_SIZE;
	private static final Method SYSTEM_LOAD_AVERAGE;
	private static final Method SYSTEM_CPU_LOAD;

	static {
		FREE_PHYSICAL_MEMORY_SIZE = getMxImplMethod("getFreePhysicalMemorySize");
		TOTAL_PHYSICAL_MEMORY_SIZE = getMxImplMethod("getTotalPhysicalMemorySize");
		FREE_SWAP_SPACE_SIZE = getMxImplMethod("getFreeSwapSpaceSize");
		TOTAL_SWAP_SPACE_SIZE = getMxImplMethod("getTotalSwapSpaceSize");
		SYSTEM_LOAD_AVERAGE = getMxImplMethod("getSystemLoadAverage");
		SYSTEM_CPU_LOAD = getMxImplMethod("getSystemCpuLoad");
	}

	private static Method getMxImplMethod(String methodName) {
		try {
			return Class.forName("com.sun.management.OperatingSystemMXBean").getMethod(methodName);
		} catch (Exception e) {
			// not available
			return null;
		}
	}

	public long getFreePhysicalMemorySize() {
		if (FREE_PHYSICAL_MEMORY_SIZE == null) {
			return -1;
		}
		try {
			return (long) FREE_PHYSICAL_MEMORY_SIZE.invoke(MX_BEAN);
		} catch (Exception e) {
			return -1;
		}
	}

	public long getTotalPhysicalMemorySize() {
		if (TOTAL_PHYSICAL_MEMORY_SIZE == null) {
			return -1;
		}
		try {
			return (long) TOTAL_PHYSICAL_MEMORY_SIZE.invoke(MX_BEAN);
		} catch (Exception e) {
			return -1;
		}
	}

	public long getFreeSwapSpaceSize() {
		if (FREE_SWAP_SPACE_SIZE == null) {
			return -1;
		}
		try {
			return (long) FREE_SWAP_SPACE_SIZE.invoke(MX_BEAN);
		} catch (Exception e) {
			return -1;
		}
	}

	public long getTotalSwapSpaceSize() {
		if (TOTAL_SWAP_SPACE_SIZE == null) {
			return -1;
		}
		try {
			return (long) TOTAL_SWAP_SPACE_SIZE.invoke(MX_BEAN);
		} catch (Exception e) {
			return -1;
		}
	}

	public double[] getSystemLoadAverage() {
		if (!OsInformation.isWindows()) {
			if (SYSTEM_LOAD_AVERAGE == null) {
				return null;
			}
			try {
				final double oneMinuteLoadAverage = (double) SYSTEM_LOAD_AVERAGE.invoke(MX_BEAN);
				return new double[] { oneMinuteLoadAverage >= 0 ? oneMinuteLoadAverage : -1, -1, -1 };
			} catch (Exception e) {
			}
		}
		return null;
	}

	public int getSystemCpuLoad() {
		if (SYSTEM_CPU_LOAD == null) {
			return -1;
		}
		try {
			double result = (double) SYSTEM_CPU_LOAD.invoke(MX_BEAN);
			if (result >= 0.0) {
				return (int) result * 100;
			}
		} catch (Exception e) {
		}
		return -1;
	}
}
