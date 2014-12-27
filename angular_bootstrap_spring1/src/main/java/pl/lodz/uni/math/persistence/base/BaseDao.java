package pl.lodz.uni.math.persistence.base;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.lodz.uni.math.persistence.dao.exception.UpdateDeleteException;
import pl.lodz.uni.math.utils.CopyUtil;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BaseDao {
	private static Logger log = LoggerFactory.getLogger(BaseDao.class);

	@PersistenceUnit
	protected EntityManagerFactory emf;


	protected EntityManagerFactory getEmf() {
		return emf;
	}
	
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}


	@SuppressWarnings("unchecked")
	protected <T extends Cloneable> Collection<T> loadData(Class<T> oType, Query query) {
		EntityManager em = emf.createEntityManager();

		try {
			List<T> data = new ArrayList<T>();
			
			Collection<T> resultList = query.getResultList();
			if (resultList != null) {
				for (T element : resultList) {
					// clone (lazy load collections and detach objects)
					data.add(CopyUtil.clone(element));
				}
			}

			return data;
		} finally {
			em.close();
		}
	}
	

	protected long countData(Query query) {
		EntityManager em = emf.createEntityManager();

		try {
			long count = (long)query.getSingleResult();
			return count;
		} finally {
			em.close();
		}
	}
	

    protected <T extends Cloneable> boolean deleteSingleData(Class<T> oType, Long id) throws UpdateDeleteException {
        EntityManager entityManager = getEmf().createEntityManager();

        try	{
            EntityTransaction tx = null;

            try {
                tx = entityManager.getTransaction();

                tx.begin();

                entityManager.remove(entityManager.find(oType, id));

                tx.commit();
            }
            catch (Exception e) {
                log.error("Exception during deleting " + id + ": " + e.getMessage());

                throw new UpdateDeleteException("Unable to delete data " + id, e);
            }
            finally	{
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            }
        }
        finally	{
            entityManager.close();
        }

        return true;
    }

    protected boolean updateSingleData(Object entity) throws UpdateDeleteException {
        EntityManager entityManager = getEmf().createEntityManager();

        try	{
            EntityTransaction tx = null;

            try {
                tx = entityManager.getTransaction();

                tx.begin();

                entityManager.merge(entity);

                tx.commit();
            }
            catch (Exception e) {
                log.error("Exception during merge: " + e.getMessage());

                throw new UpdateDeleteException("Unable to merge data", e);
            }
            finally	{
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            }
        }
        finally	{
            entityManager.close();
        }

        return true;
    }
}
