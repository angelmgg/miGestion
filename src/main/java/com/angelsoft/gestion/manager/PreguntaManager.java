package com.angelsoft.gestion.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.angelsoft.gestion.bean.auxiliares.Pregunta;
import com.angelsoft.gestion.dao.PreguntaDao;

@SuppressWarnings("serial")
public class PreguntaManager implements Serializable{
	
	private static Logger logger; 
	{
		logger = Logger.getLogger(this.getClass().getName());
	}
	private final HashMap<Long, Pregunta> preguntas = new HashMap<>();
	private static PreguntaManager instance;
	private long nextId = 0;
	
	private PreguntaManager() {
	}
	
	/**
	 * @return 
	 */
	public static PreguntaManager getInstance() {
		if (instance == null) {
			instance = new PreguntaManager();
			instance.recogeDatos();
		}
		return instance;
	}
	
	/**
	 * @return 
	 */
	public synchronized List<Pregunta> findAll() {
		return findAll(null);
	}
	/**
	 * 
	 */
	public synchronized List<Pregunta> findAll(String stringFilter) {
		final ArrayList<Pregunta> arrayList = new ArrayList<>();
		for (final Pregunta preguntaAux : preguntas.values()) {
			try {
				final boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
						|| preguntaAux.toString().toLowerCase().contains(stringFilter.toLowerCase());
				if (passesFilter) {
					arrayList.add(preguntaAux.clone());
				}
			} catch (final CloneNotSupportedException ex) {
				logger.log(Level.SEVERE, "findAll(String stringFilter) " + ex.toString());
			}
		}
		
		Collections.sort(arrayList, new Comparator<Pregunta>() {

			@Override
			public int compare(Pregunta o1, Pregunta o2) {
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
			PreguntaDao preguntaDao = new PreguntaDao();
			
			List<Pregunta> lista = preguntaDao.traePreguntas();
			for(Pregunta preguntaAux: lista){
				save(preguntaAux);
			}
		}
	}

	/**
	 */
	public synchronized void save(Pregunta entry) {
		if (entry == null) {
			logger.log(Level.SEVERE, "Pregunta es null. ");
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
		preguntas.put(entry.getIde(), entry);
	}
	
	/**
	 */
	public synchronized List<Pregunta> findAll(String stringFilter, int start, int maxresults) {
		final ArrayList<Pregunta> arrayList = new ArrayList<>();
		for (final Pregunta preguntaAux : preguntas.values()) {
			try {
				final boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
						|| preguntaAux.toString().toLowerCase().contains(stringFilter.toLowerCase());
				if (passesFilter) {
					arrayList.add(preguntaAux.clone());
				}
			} catch (final CloneNotSupportedException ex) {
				logger.log(Level.SEVERE, "findAll(String stringFilter, int start, int maxresults) " + ex.toString());
			}
		}
		Collections.sort(arrayList, new Comparator<Pregunta>() {

			@Override
			public int compare(Pregunta o1, Pregunta o2) {
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
	public synchronized void delete(Pregunta value) {
		preguntas.remove(value.getIde());
	}
	
	/**
	 */
	public synchronized long count() {
		return preguntas.size();
	}
	
}
