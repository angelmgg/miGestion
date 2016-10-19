package com.angelsoft.gestion.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.angelsoft.gestion.bean.auxiliares.InicializacionCodigo;
import com.angelsoft.gestion.dao.InicializacionCodigoDao;

@SuppressWarnings("serial")
public class InicializacionManager implements Serializable {
	
	private static Logger logger; 
	{
		logger = Logger.getLogger(this.getClass().getName());
	}
	private final HashMap<Long, InicializacionCodigo> inicializacionCodigos = new HashMap<>();
	private static InicializacionManager instance;
	private long nextId = 0;
	
	private InicializacionManager() {
	}
	
	/**
	 * @return 
	 */
	public static InicializacionManager getInstance() {
		if (instance == null) {
			instance = new InicializacionManager();
			instance.recogeDatos();
		}
		return instance;
	}
	
	/**
	 * @return 
	 */
	public synchronized List<InicializacionCodigo> findAll() {
		return findAll(null);
	}
	/**
	 * 
	 */
	public synchronized List<InicializacionCodigo> findAll(String stringFilter) {
		final ArrayList<InicializacionCodigo> arrayList = new ArrayList<>();
		for (final InicializacionCodigo inicializacionCodigoAux : inicializacionCodigos.values()) {
			try {
				final boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
						|| inicializacionCodigoAux.toString().toLowerCase().contains(stringFilter.toLowerCase());
				if (passesFilter) {
					arrayList.add(inicializacionCodigoAux.clone());
				}
			} catch (final CloneNotSupportedException ex) {
				logger.log(Level.SEVERE, "findAll(String stringFilter) " + ex.toString());
			}
		}
		
		Collections.sort(arrayList, new Comparator<InicializacionCodigo>() {

			@Override
			public int compare(InicializacionCodigo o1, InicializacionCodigo o2) {
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
			InicializacionCodigoDao inicializacionCodigoDao = new InicializacionCodigoDao();
			
			List<InicializacionCodigo> lista = inicializacionCodigoDao.traeInicializacionCodigos();
			for(InicializacionCodigo inicializacionCodigoAux: lista){
				save(inicializacionCodigoAux);
			}
		}
	}

	/**
	 */
	public synchronized void save(InicializacionCodigo entry) {
		if (entry == null) {
			logger.log(Level.SEVERE, "InicializacionCodigo es null. ");
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
		inicializacionCodigos.put(entry.getIde(), entry);
	}
	
	/**
	 */
	public synchronized List<InicializacionCodigo> findAll(String stringFilter, int start, int maxresults) {
		final ArrayList<InicializacionCodigo> arrayList = new ArrayList<>();
		for (final InicializacionCodigo inicializacionCodigoAux : inicializacionCodigos.values()) {
			try {
				final boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
						|| inicializacionCodigoAux.toString().toLowerCase().contains(stringFilter.toLowerCase());
				if (passesFilter) {
					arrayList.add(inicializacionCodigoAux.clone());
				}
			} catch (final CloneNotSupportedException ex) {
				logger.log(Level.SEVERE, "findAll(String stringFilter, int start, int maxresults) " + ex.toString());
			}
		}
		Collections.sort(arrayList, new Comparator<InicializacionCodigo>() {

			@Override
			public int compare(InicializacionCodigo o1, InicializacionCodigo o2) {
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
	public synchronized void delete(InicializacionCodigo value) {
		inicializacionCodigos.remove(value.getIde());
	}
	
	/**
	 */
	public synchronized long count() {
		return inicializacionCodigos.size();
	}
	
}
