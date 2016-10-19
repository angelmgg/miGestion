package com.angelsoft.gestion.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.angelsoft.gestion.bean.auxiliares.Tarea;
import com.angelsoft.gestion.dao.TareaDao;

@SuppressWarnings("serial")
public class TareaManager implements Serializable {
	
	private static Logger logger; 
	{
		logger = Logger.getLogger(this.getClass().getName());
	}
	private final HashMap<Long, Tarea> tareas = new HashMap<>();
	private static TareaManager instance;
	private long nextId = 0;
	
	private TareaManager() {
	}
	
	/**
	 * @return 
	 */
	public static TareaManager getInstance() {
		if (instance == null) {
			instance = new TareaManager();
			instance.recogeDatos();
		}
		return instance;
	}
	
	/**
	 * @return 
	 */
	public synchronized List<Tarea> findAll() {
		return findAll(null);
	}
	/**
	 * 
	 */
	public synchronized List<Tarea> findAll(String stringFilter) {
		final ArrayList<Tarea> arrayList = new ArrayList<>();
		for (final Tarea tareaAux : tareas.values()) {
			try {
				final boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
						|| tareaAux.toString().toLowerCase().contains(stringFilter.toLowerCase());
				if (passesFilter) {
					arrayList.add(tareaAux.clone());
				}
			} catch (final CloneNotSupportedException ex) {
				logger.log(Level.SEVERE, "findAll(String stringFilter) " + ex.toString());
			}
		}
		
		Collections.sort(arrayList, new Comparator<Tarea>() {

			@Override
			public int compare(Tarea o1, Tarea o2) {
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
			TareaDao tareaDao = new TareaDao();
			
			List<Tarea> lista = tareaDao.traeTareas();
			for(Tarea tareaAux: lista){
				save(tareaAux);
			}
		}
	}

	/**
	 */
	public synchronized void save(Tarea entry) {
		if (entry == null) {
			logger.log(Level.SEVERE, "Tarea es null. ");
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
		tareas.put(entry.getIde(), entry);
	}
	
	/**
	 */
	public synchronized List<Tarea> findAll(String stringFilter, int start, int maxresults) {
		final ArrayList<Tarea> arrayList = new ArrayList<>();
		for (final Tarea tareaAux : tareas.values()) {
			try {
				final boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
						|| tareaAux.toString().toLowerCase().contains(stringFilter.toLowerCase());
				if (passesFilter) {
					arrayList.add(tareaAux.clone());
				}
			} catch (final CloneNotSupportedException ex) {
				logger.log(Level.SEVERE, "findAll(String stringFilter, int start, int maxresults) " + ex.toString());
			}
		}
		Collections.sort(arrayList, new Comparator<Tarea>() {

			@Override
			public int compare(Tarea o1, Tarea o2) {
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
	public synchronized void delete(Tarea value) {
		tareas.remove(value.getIde());
	}
	
	/**
	 */
	public synchronized long count() {
		return tareas.size();
	}
	
}
