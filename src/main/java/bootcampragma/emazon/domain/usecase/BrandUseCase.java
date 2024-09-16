package bootcampragma.emazon.domain.usecase;

import bootcampragma.emazon.domain.api.IBrandServicePort;
import bootcampragma.emazon.domain.entity.Brand;
import bootcampragma.emazon.domain.exception.category.InvalidSortDirectionException;
import bootcampragma.emazon.domain.spi.IBrandPersistencePort;
import bootcampragma.emazon.domain.exception.brand.BrandAlreadyExistException;
import bootcampragma.emazon.domain.util.Constants;
import bootcampragma.emazon.domain.util.CustomPage;


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
        if (page < Constants.ZERO ){
            throw new IllegalArgumentException(Constants.PAGE_NUMBER_EXCEPTION);
        }
        if (!sortDirection.equalsIgnoreCase(Constants.ASC) && !sortDirection.equalsIgnoreCase(Constants.DESC)){
            throw new InvalidSortDirectionException(Constants.INVALID_SORT_DIRECTION_EXCEPTION);
        }
        if(size == null || size <Constants.ZERO){
            throw new IllegalArgumentException(Constants.INVALID_SIZE_EXCEPTION);
        }
        return brandPersistencePort.getAllBrand(page, size, sortDirection);
    }
}
