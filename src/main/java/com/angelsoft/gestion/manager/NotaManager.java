package com.angelsoft.gestion.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.angelsoft.gestion.bean.auxiliares.Nota;
import com.angelsoft.gestion.dao.NotaDao;

@SuppressWarnings("serial")
public class NotaManager implements Serializable {
	
	private static Logger logger; 
	{
		logger = Logger.getLogger(this.getClass().getName());
	}
	private final HashMap<Long, Nota> notas = new HashMap<>();
	private static NotaManager instance;
	private long nextId = 0;
	
	private NotaManager() {
	}
	
	/**
	 * @return 
	 */
	public static NotaManager getInstance() {
		if (instance == null) {
			instance = new NotaManager();
			instance.recogeDatos();
		}
		return instance;
	}
	
	/**
	 * @return 
	 */
	public synchronized List<Nota> findAll() {
		return findAll(null);
	}
	/**
	 * 
	 */
	public synchronized List<Nota> findAll(String stringFilter) {
		final ArrayList<Nota> arrayList = new ArrayList<>();
		for (final Nota notaAux : notas.values()) {
			try {
				final boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
						|| notaAux.toString().toLowerCase().contains(stringFilter.toLowerCase());
				if (passesFilter) {
					arrayList.add(notaAux.clone());
				}
			} catch (final CloneNotSupportedException ex) {
				logger.log(Level.SEVERE, "findAll(String stringFilter) " + ex.toString());
			}
		}
		
		Collections.sort(arrayList, new Comparator<Nota>() {

			@Override
			public int compare(Nota o1, Nota o2) {
				return (int) (o2.getIde() - o1.getIde());
			}
		});
		return arrayList;
	}
		
	/**
	 * 
	 */
	public void recogeDatos() {
		if (findAll().isEmpty()) {
			NotaDao notaDao = new NotaDao();
			
			List<Nota> lista = notaDao.traeNotas();
			for(Nota notaAux: lista){
				save(notaAux);
			}
		}
	}

	/**
	 */
	public synchronized void save(Nota entry) {
		if (entry == null) {
			logger.log(Level.SEVERE, "Nota es null. ");
			return;
		}
		if (entry.getIde() == null) {
			entry.setIde(nextId++);
		}
		
		try {
			entry = entry.clone();
		} catch (final Exception ex) {
			throw new RuntimeException(ex);
		}
		notas.put(entry.getIde(), entry);
	}
	
	/**
	 */
	public synchronized List<Nota> findAll(String stringFilter, int start, int maxresults) {
		final ArrayList<Nota> arrayList = new ArrayList<>();
		for (final Nota notaAux : notas.values()) {
			try {
				final boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
						|| notaAux.toString().toLowerCase().contains(stringFilter.toLowerCase());
				if (passesFilter) {
					arrayList.add(notaAux.clone());
				}
			} catch (final CloneNotSupportedException ex) {
				logger.log(Level.SEVERE, "findAll(String stringFilter, int start, int maxresults) " + ex.toString());
			}
		}
		Collections.sort(arrayList, new Comparator<Nota>() {

			@Override
			public int compare(Nota o1, Nota o2) {
				return (int) (o2.getIde() - o1.getIde());
			}
		});
		int end = start + maxresults;
		if (end > arrayList.size()) {
			end = arrayList.size();
		}
		return arrayList.subList(start, end);
	}

	/**
	 */
	public synchronized void delete(Nota value) {
		notas.remove(value.getIde());
	}
	
	/**
	 */
	public synchronized long count() {
		return notas.size();
	}
	
}
