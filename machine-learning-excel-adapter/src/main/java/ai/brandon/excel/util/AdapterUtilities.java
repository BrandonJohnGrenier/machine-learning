package ai.brandon.excel.util;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public final class AdapterUtilities {

	private AdapterUtilities() {

	}

	public static boolean isExcelFile(String filename) {
		if (isEmpty(filename)) {
			return false;
		}
		if (filename.endsWith(".xls")) {
			return true;
		}
		if (filename.endsWith(".xlsx")) {
			return true;
		}
		return false;
	}

}
