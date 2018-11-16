package com.play18xx.app;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.play18xx.material.Basic;

public class App 
{
	public static boolean verbose = true;
	public static String gamename = "Play18xx";
	
	public static void main( String[] args )
    {
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		System.out.println("Current relative path is: " + s);

        System.out.println( "Starting Application - play18xx" );
		new Basic();

    }
}
