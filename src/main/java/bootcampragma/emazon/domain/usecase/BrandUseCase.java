package bootcampragma.emazon.domain.usecase;

import bootcampragma.emazon.domain.api.IBrandServicePort;
import bootcampragma.emazon.domain.entity.Brand;
import bootcampragma.emazon.domain.spi.IBrandPersistencePort;
import bootcampragma.emazon.domain.util.CustomPage;
import bootcampragma.emazon.domain.exception.brand.BrandAlreadyExistException;


public class BrandUseCase implements IBrandServicePort {
    private final IBrandPersistencePort brandPersistencePort;

    public BrandUseCase(IBrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }


    @Override
    public void saveBrand(Brand brand) {
        if(brandPersistencePort.findByName(brand.getName())){
            throw new BrandAlreadyExistException();
        }
        brandPersistencePort.saveBrand(brand);
    }


    @Override
    public CustomPage<Brand> getAllBrand(Integer page, Integer size, String sortDirection) {
        return brandPersistencePort.getAllBrand(page, size, sortDirection);
    }

}
