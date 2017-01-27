package ai.brandon.commons;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FastLogger implements Log {

	private final Log log;

	public static FastLogger newInstance(Class<? extends Object> classToLog) {
		return new FastLogger(classToLog);
	}

	FastLogger(Class<? extends Object> classToLog) {
		log = LogFactory.getLog(classToLog);
	}

	public void debug(Object arg0, Throwable arg1) {
		log.debug(arg0, arg1);
	}

	public void debug(Object arg0) {
		if (log.isDebugEnabled()) {
			log.debug(arg0);
		}
	}

	public void debug(String message, Object... args) {
		if (log.isDebugEnabled()) {
			log.debug(String.format(message, args));
		}
	}

	public void error(Object arg0, Throwable arg1) {
		log.error(arg0, arg1);
	}

	public void error(Object arg0) {
		log.error(arg0);
	}

	public void error(String message, Object... args) {
		log.error(String.format(message, args));
	}

	public void fatal(Object arg0, Throwable arg1) {
		log.fatal(arg0, arg1);
	}

	public void fatal(Object arg0) {
		log.fatal(arg0);
	}

	public void fatal(String message, Object... args) {
		log.fatal(String.format(message, args));
	}

	public void info(Object arg0, Throwable arg1) {
		log.info(arg0, arg1);
	}

	public void info(Object arg0) {
		log.info(arg0);
	}

	public void info(String message, Object... args) {
		log.info(String.format(message, args));
	}

	public boolean isDebugEnabled() {
		return log.isDebugEnabled();
	}

	public boolean isErrorEnabled() {
		return log.isErrorEnabled();
	}

	public boolean isFatalEnabled() {
		return log.isFatalEnabled();
	}

	public boolean isInfoEnabled() {
		return log.isInfoEnabled();
	}

	public boolean isTraceEnabled() {
		return log.isTraceEnabled();
	}

	public boolean isWarnEnabled() {
		return log.isWarnEnabled();
	}

	public void trace(Object arg0, Throwable arg1) {
		log.trace(arg0, arg1);
	}

	public void trace(Object arg0) {
		log.trace(arg0);
	}

	public void trace(String message, Object... args) {
		log.trace(String.format(message, args));
	}

	public void warn(Object arg0, Throwable arg1) {
		log.warn(arg0, arg1);
	}

	public void warn(Object arg0) {
		log.warn(arg0);
	}

	public void warn(String message, Object... args) {
		log.warn(String.format(message, args));
	}

}
