package com.angelsoft.gestion.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.angelsoft.gestion.bean.entidades.Ddm;
import com.angelsoft.gestion.dao.DdmDao;

@SuppressWarnings("serial")
public class DdmManager implements Serializable {
	
	private static Logger logger; 
	{
		logger = Logger.getLogger(this.getClass().getName());
	}
	private final HashMap<Long, Ddm> ddms = new HashMap<>();
	private static DdmManager instance;
	private long nextId = 0;
	
	private DdmManager() {
	}
	
	/**
	 * @return 
	 */
	public static DdmManager getInstance() {
		if (instance == null) {
			instance = new DdmManager();
			instance.recogeDatos();
		}
		return instance;
	}
	
	/**
	 * @return 
	 */
	public synchronized List<Ddm> findAll() {
		return findAll(null);
	}
	/**
	 * 
	 */
	public synchronized List<Ddm> findAll(String stringFilter) {
		final ArrayList<Ddm> arrayList = new ArrayList<>();
		for (final Ddm ddmAux : ddms.values()) {
			try {
				final boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
						|| ddmAux.toString().toLowerCase().contains(stringFilter.toLowerCase());
				if (passesFilter) {
					arrayList.add(ddmAux.clone());
				}
			} catch (final CloneNotSupportedException ex) {
				Logger.getLogger(DdmManager.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		
		Collections.sort(arrayList, new Comparator<Ddm>() {

			@Override
			public int compare(Ddm o1, Ddm o2) {
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
			DdmDao ddmDao = new DdmDao();
			
			List<Ddm> lista = ddmDao.traeDdms();
			for(Ddm ddmAux: lista){
				save(ddmAux);
			}
		}
	}

	/**
	 * Persists or updates customer in the system. Also assigns an identifier
	 * for new Customer instances.
	 *
	 * @param entry
	 */
	public synchronized void save(Ddm entry) {
		if (entry == null) {
			logger.log(Level.SEVERE, "Dmd es null. ");
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
		ddms.put(entry.getIde(), entry);
	}
	
	/**
	 */
	public synchronized List<Ddm> findAll(String stringFilter, int start, int maxresults) {
		final ArrayList<Ddm> arrayList = new ArrayList<>();
		for (final Ddm ddmAux : ddms.values()) {
			try {
				final boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
						|| ddmAux.toString().toLowerCase().contains(stringFilter.toLowerCase());
				if (passesFilter) {
					arrayList.add(ddmAux.clone());
				}
			} catch (final CloneNotSupportedException ex) {
				Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
			}
		}
		Collections.sort(arrayList, new Comparator<Ddm>() {

			@Override
			public int compare(Ddm o1, Ddm o2) {
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
	public synchronized void delete(Ddm value) {
		ddms.remove(value.getIde());
	}
	
	/**
	 */
	public synchronized long count() {
		return ddms.size();
	}
	
}
