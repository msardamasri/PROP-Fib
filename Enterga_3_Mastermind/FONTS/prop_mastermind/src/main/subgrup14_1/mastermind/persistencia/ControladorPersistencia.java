package main.subgrup14_1.mastermind.persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import main.subgrup14_1.mastermind.excepcions.ExcepcioPersistencia;

/**
 * @author Pol Kallai Raventos (pol.kallai@estudiantat.upc.edu)
 */
public class ControladorPersistencia {
	
	/**
	 * Llegeix objecte de disc
	 * @param path ruta de l'arxiu
	 * @throws ExcepcioPersistencia Si el fitxer no existeix o hi ha un problema a l'escriure en ell
	 * @return Objecte llegit
	 */
	public Object llegeix(String path) throws ExcepcioPersistencia {
		Object obj;  
		try {
			File file = new File(path);
			if (!file.exists()) {
			    escriu(new HashMap<>(), path);
			}
			FileInputStream fileInputStream = new FileInputStream(path);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			obj = objectInputStream.readObject();
			objectInputStream.close();
	      }
		catch (ClassNotFoundException cnfe) {
			throw new ExcepcioPersistencia("L'arxiu esta corrupte");
		}
		catch (IOException ioe) {
			throw new ExcepcioPersistencia("L'arxiu no existeix o esta corrupte");
		}
		return obj;
	}
	
	/**
	 * Escriu objecte de disc
	 * @param obj Objecte a escriure
	 * @param path ruta de l'arxiu
	 * @throws ExcepcioPersistencia Si el fitxer no existeix o hi ha un problema a l'escriure en ell
	 */
	public void escriu(Object obj, String path) throws ExcepcioPersistencia {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(path, false);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(obj);
			objectOutputStream.flush();
			objectOutputStream.close();
		}
		catch (IOException e) {
			throw new ExcepcioPersistencia("L'arxiu no existeix o esta corrupte");
		}
	}
}