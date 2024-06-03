package com.kh.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;


public class MyFileRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File origin) {
		
		String originName = origin.getName();
		
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
		int ranNum=(int)(Math.random()*90000)+10000;
		
		String ext=originName.substring(originName.lastIndexOf("."));
		
		String changeName= time+ranNum+ext;
		
		
		
		return new File(origin.getParent(),changeName);
		
	}
}
