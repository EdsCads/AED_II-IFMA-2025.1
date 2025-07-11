package main;

import java.io.*;

public class Main {
	public static void main(String args[])
	 {
		String caminho = ".\\Entrada\\Dados.txt";
		System.out.println(caminho);
		try(BufferedReader leitor = new BufferedReader(new FileReader(caminho))){
			
			while(leitor.ready()) {
				System.out.println(leitor.readLine());
			}
			
		}catch(Exception e) {
			System.out.println(e.getStackTrace());
		}
		
	}
}
