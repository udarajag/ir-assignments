package com.ntu.ir.main;

public class FilterMain2 {
	
	//"scala","../1000.xml"
	public static void main(String[] args) throws Exception {
		if(args.length < 2)
			throw new Exception("Invalid number of args");
		FilterDriver2 fd = new FilterDriver2();
		fd.init(args[0],args[1]);
		fd.run();
	}

}
