package analizadorSemantico;

import java.util.Vector;

public class Expresion {
	public enum Operacion {SUMA, RESTA, MULTIPLICACION, DIVISION};
	Operacion operador;
	Double valorIzquierdo;
	Double valorDerecho;
	Double resultado;
	
	public Expresion(Operacion operador, Double valorIzquierdo, Double valorDerecho) {
		this.operador = operador;
		this.valorIzquierdo = valorIzquierdo;
		this.valorDerecho = valorDerecho;
		ejecutarOperacion();
	}
	
	public Expresion(String expresion) {
		String simbolos[] = expresion.split("\\s");
		if(simbolos.length > 3) {
			expresion = reducirExpresion(expresion);
		}
		
		simbolos = expresion.split("\\s");
		System.out.println(simbolos[0] + simbolos[1] + simbolos[2] + "tamaño:" + simbolos.length);
		
		if(simbolos.length < 3) {
			System.out.println("Epresion no válida");
		} else if(simbolos.length == 3) {
			this.operador = obtenerTipoDeOperacion(simbolos[0]);
			this.valorIzquierdo = Double.valueOf(simbolos[1]);
			this.valorDerecho = Double.valueOf(simbolos[2]);
		} 
		ejecutarOperacion();
	}
	
	public Expresion(Operacion operador, Expresion expresionIzquierda, Expresion expresionDerecha) {
		this.operador = operador;
		this.valorIzquierdo = expresionIzquierda.resultado;
		this.valorDerecho = expresionDerecha.resultado;
		ejecutarOperacion();
	}
	
	public void ejecutarOperacion() {
		switch(this.operador) {
			case SUMA:
				this.resultado = this.valorIzquierdo + this.valorDerecho;
			break;
			
			case RESTA:
				this.resultado = this.valorIzquierdo - this.valorDerecho;
			break;
			
			case MULTIPLICACION:
				this.resultado = this.valorIzquierdo * this.valorDerecho;
			break;
				
			case DIVISION:
				this.resultado = this.valorIzquierdo / this.valorDerecho;
			break;
		}
	}
		
	public Operacion obtenerTipoDeOperacion(String operador) {
		Operacion tipoDeOperacion = Operacion.SUMA;
		switch(operador.trim()) {
			case "+":
				this.operador = Operacion.SUMA;
				tipoDeOperacion = Operacion.SUMA;
			break;
			
			case "-":
				this.operador = Operacion.RESTA;
				tipoDeOperacion = Operacion.RESTA;
			break;
			
			case "*":
				this.operador = Operacion.MULTIPLICACION;
				tipoDeOperacion = Operacion.MULTIPLICACION;
			break;
			
			case "/":
				this.operador = Operacion.DIVISION;
				tipoDeOperacion = Operacion.DIVISION;
			break;		
		}
		return tipoDeOperacion;
	}
	
	public String reducirExpresion(String expresion) {
		String valores[] = expresion.split("\\s");
		int ultimoIndiceDeOperador;
		Vector<String> expresionFinal = new Vector<String>();
		
		for(int contador = 0; contador < valores.length; contador++) {
			expresionFinal.add(valores[contador]);
		}
		
		while(expresionFinal.size() > 3) {
			String expresionInterna;
			Expresion nuevaExpresion;
			ultimoIndiceDeOperador = obtenerUltimoIndiceDeOperador(expresionFinal);
			expresionInterna = expresionFinal.remove(ultimoIndiceDeOperador) + " " + expresionFinal.remove(ultimoIndiceDeOperador) + " " + expresionFinal.get(ultimoIndiceDeOperador);
			nuevaExpresion = new Expresion(expresionInterna);
			expresionFinal.add(ultimoIndiceDeOperador, nuevaExpresion.resultado.toString());
			expresionFinal.remove(ultimoIndiceDeOperador + 1);
			System.out.println(ultimoIndiceDeOperador);
			System.out.println(expresionFinal.size());
			System.out.println(expresionInterna);
			System.out.println(expresionFinal.toString());
		}
		if(expresionFinal.size() == 2) {
			expresionFinal.add(0, "+");
		}
		return expresionFinal.get(0) + " " + expresionFinal.get(1) + " " + expresionFinal.get(2);
	}
	
	public int obtenerUltimoIndiceDeOperador(Vector<String> valores) {
		for(int contador = valores.size() - 1; contador > -1; contador--) {
			if(valores.get(contador).equals("+") || valores.get(contador).equals("-") || valores.get(contador).equals("*") || valores.get(contador).equals("/")) {
				return contador;
			}
		}
		return -1;
	}
}
