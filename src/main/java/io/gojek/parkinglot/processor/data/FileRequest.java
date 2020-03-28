/**
 * 
 */
package io.gojek.parkinglot.processor.data;

import java.io.File;

/**
 * @author vaibhav.singh
 *
 */
public class FileRequest implements Request
{
	private File file = null;

	public FileRequest(File file)
	{
		this.file = file;
	}

	/**
	 * @return the file
	 */
	public File getFile()
	{
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(File file)
	{
		this.file = file;
	}
}
