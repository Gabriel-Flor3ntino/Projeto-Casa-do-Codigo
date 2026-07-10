package br.com.casadocodigo.loja.daos;

import br.com.casadocodigo.loja.models.Category;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CategoryDAO {

    @PersistenceContext
    private EntityManager manager;

    public List<Category> list(){

        return manager.createQuery(
                "from Category",
                Category.class
        ).getResultList();

    }

}