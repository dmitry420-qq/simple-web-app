package data;

import java.util.Collection;

public interface OfficiantsDAO {
    boolean insert(Officiant officiant);

    boolean delete(Officiant officiant);

    Officiant findByID(int id);

    boolean update(Officiant officiant);

    boolean saveOrUpdate(Officiant o);

    Collection<Officiant> findByName(String firstName,
                                     String secondName);

}
