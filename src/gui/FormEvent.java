package gui;

import java.io.File;
import java.util.EventObject;

public class FormEvent extends EventObject {
	private File file;
	private Boolean viewLog;
	private String exportRoute;
	
	public FormEvent(Object source){
		super(source);
	}

	public FormEvent(Object source, File file, Boolean viewLog, String exportRoute) {
		super(source);
		this.file = file;
		this.viewLog = viewLog;
		this.exportRoute = exportRoute;
	}
	
	public Boolean getViewLog() {
		return viewLog;
	}

	public String getExportRoute() {
		return exportRoute;
	}

	public void setExportRoute(String exportRoute) {
		this.exportRoute = exportRoute;
	}

	public void setViewLog(Boolean viewLog) {
		this.viewLog = viewLog;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
