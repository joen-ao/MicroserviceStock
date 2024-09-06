package bootcampragma.emazon.infrastructure.output.jpa.adapter;

import bootcampragma.emazon.domain.entity.Brand;
import bootcampragma.emazon.domain.spi.IBrandPersistencePort;
import bootcampragma.emazon.domain.util.CustomPage;
import bootcampragma.emazon.infrastructure.output.jpa.entity.BrandEntity;
import bootcampragma.emazon.infrastructure.output.jpa.mapper.BrandEntityMapper;
import bootcampragma.emazon.infrastructure.output.jpa.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BrandJpaAdapter implements IBrandPersistencePort {

    private final IBrandRepository brandRepository;
    private final BrandEntityMapper brandEntityMapper;


    @Override
    public void saveBrand(Brand brand) {
        brandRepository.save(brandEntityMapper.toBrandEntity(brand));
    }

    @Override
    public CustomPage<Brand> getAllBrand(Integer page, Integer size, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), "name");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<BrandEntity> brandEntityPage = brandRepository.findAll(pageable);
        return brandEntityMapper.toCustomPage(brandEntityPage);
    }

    @Override
    public Optional<Brand> findByName(String name) {
        return brandRepository.findByName(name).map(brandEntityMapper::toDomainBrand);
    }

    @Override
    public Optional<Brand> findById(Long id) {
        return brandRepository.findById(id).map(brandEntityMapper::toDomainBrand);
    }
}
