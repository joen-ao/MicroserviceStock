package bootcampragma.emazon.domain.spi;

import bootcampragma.emazon.domain.entity.Brand;
import bootcampragma.emazon.domain.util.CustomPage;

import java.util.Optional;

public interface IBrandPersistencePort {
    void saveBrand(Brand brand);
    CustomPage<Brand> getAllBrand(Integer page, Integer size, String sortDirection);
    Optional<Brand> findById(Long id);
    Optional<Brand> findByName(String name);
}
