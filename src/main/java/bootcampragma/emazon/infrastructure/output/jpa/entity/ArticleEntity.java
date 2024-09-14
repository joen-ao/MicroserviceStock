package bootcampragma.emazon.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = ArticleEntity.TABLE_NAME )
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ArticleEntity {
    public static final String TABLE_NAME = "article";
    public static final String JOIN_COLUMN_BRAND_ID = "brand_id";
    public static final String JOIN_TABLE_ARTICLE_CATEGORY = "article_category";
    public static final String JOIN_COLUMN_ARTICLE_ID = "article_id";
    public static final String JOIN_COLUMN_CATEGORY_ID = "category_id";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( nullable = false, unique = true, length = 60)
    private String name;

    @Column(nullable = false, length = 90)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column( nullable = false)
    private Long stock;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = JOIN_COLUMN_BRAND_ID)
    private BrandEntity brandEntity;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = JOIN_TABLE_ARTICLE_CATEGORY,
            joinColumns = @JoinColumn(name = JOIN_COLUMN_ARTICLE_ID),
            inverseJoinColumns = @JoinColumn(name = JOIN_COLUMN_CATEGORY_ID))
    private List<CategoryEntity> categoriesEntity;

}
