package bootcampragma.emazon.domain.spi;

import bootcampragma.emazon.domain.entity.Brand;

public interface IBrandPersistencePort {
    void saveBrand(Brand brand);
}
