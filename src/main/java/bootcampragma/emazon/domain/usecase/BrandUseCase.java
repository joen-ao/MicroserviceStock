package bootcampragma.emazon.domain.usecase;

import bootcampragma.emazon.domain.api.IBrandServicePort;
import bootcampragma.emazon.domain.entity.Brand;
import bootcampragma.emazon.domain.spi.IBrandPersistencePort;
import bootcampragma.emazon.infrastructure.exception.brand.BrandAlreadyExistException;
import bootcampragma.emazon.infrastructure.output.jpa.repository.IBrandRepository;


public class BrandUseCase implements IBrandServicePort {
    private final IBrandPersistencePort brandPersistencePort;
    private final IBrandRepository brandRepository;

    public BrandUseCase(IBrandPersistencePort brandPersistencePort, IBrandRepository brandRepository) {
        this.brandPersistencePort = brandPersistencePort;
        this.brandRepository = brandRepository;
    }


    @Override
    public void saveBrand(Brand brand) {
        if(brandRepository.existsByName(brand.getName())){
            throw new BrandAlreadyExistException();
        }
        brandPersistencePort.saveBrand(brand);
    }
}
