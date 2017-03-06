package analizadorSemantico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LeerDocumento implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String nombreDeArchivo = AnalizadorSemantico.rutaDeArchivo.getText().trim();
		try {
			AnalizadorSemantico.resultadoAnalisisLexico.setText(AnalizadorSemantico.analizar(AnalizadorSemantico.leerArchivo(nombreDeArchivo)));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		AnalizadorSemantico.resultadoAnalisisSintactico.setText(AnalizadorSemantico.errores.toString());
	}

}
