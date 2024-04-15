package org.cataloguemicroservice;

import lombok.AllArgsConstructor;
import org.cataloguemicroservice.entities.Category;
import org.cataloguemicroservice.entities.Product;
import org.cataloguemicroservice.repositories.ProductRepository;
import org.cataloguemicroservice.services.ICategoryService;
import org.cataloguemicroservice.services.IProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
@AllArgsConstructor
public class CatalogueMicroserviceApplication {
    @Qualifier("categoryService")
    private final ICategoryService iCategoryService;
    private final IProductService iProductService;
    private final ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(CatalogueMicroserviceApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {

            Product product1 = Product.builder()
                    .productId(1L)
                    .label("PLYO SOFT BOX")
                    .productTitle("PLYO SOFT BOX")
                    .imageUrl("https://sport-equipement.shop/product-category/muscu/equipements/")
                    .idCategory(3L)
                    .valid(true)
                    .description("Le Set plyobox mousse permet des entraînements pour tous les niveaux et toutes les puissances. Pour augmenter les difficultés des exercices, il suffit d'empiler des plyobox supplémentaires et les connecter entre elles avec leurs bandes velcro.")
                    .price((long) 811.19)
                    .build();

            Product product2 = Product.builder()
                    .productId(2L)
                    .label("PLYO BOX")
                    .productTitle("PLYO BOX")
                    .imageUrl("https://sport-equipement.shop/product/plyo-box/")
                    .idCategory(3L)
                    .valid(true)
                    .description("Box en bois qui permet d'effectuer un travail de pliométrie pendant vos séances de cross training. Un usage régulier vous permettra d'améliorer facilement votre condition physique.")
                    .price((long) 147.59)
                    .build();

            Product product3 = Product.builder()
                    .productId(3L)
                    .label("ADJUSTABLE BENCH")
                    .productTitle("ADJUSTABLE BENCH")
                    .imageUrl("https://sport-equipement.shop/product/ajustable-bench/")
                    .idCategory(3L)
                    .valid(true)
                    .description("Notre Adjustable Bench est équipé d’un dossier matelassé réglable en 10 positions (de 0 à 85 degrés) et d’un siège réglable en 3 positions (0, 15 et 30 degrés).\n\nIl existe un très petit espace entre le coussin du siège et celui du dossier, qui permet aux athlètes de réaliser des exercices de mouvements fonctionnels ainsi que de développé  couché, en positions neutre ou inclinée, sans devoir faire face aux gènes causées par un banc disjoint classique.\n\nLe modèle standard de ce banc est livré avec des pieds en caoutchouc de qualité.")
                    .price((long) 781.19)
                    .build();

            Product product4 = Product.builder()
                    .productId(4L)
                    .label("TRAP BAR")
                    .productTitle("TRAP BAR")
                    .imageUrl("https://sport-equipement.shop/product/trap-bar/")
                    .idCategory(3L)
                    .valid(true)
                    .description("La Trap Bar est le plus souvent utilisée pour les shrugs et le soulevé de terre (deadlift) afin de réduire le stress sur les lombaires.")
                    .price((long) 297.59)
                    .build();

            Product product5 = Product.builder()
                    .productId(5L)
                    .label("BARRE COMPÉTITION HOMME")
                    .productTitle("BARRE COMPÉTITION HOMME")
                    .imageUrl("https://sport-equipement.shop/product/bar-competition-homme/")
                    .idCategory(3L)
                    .valid(true)
                    .description("La barre se caractérise par une adaptation aux tolérances de poids très strictes et un moletage prononcé adapté aux tentatives de type 1 répétition max.")
                    .price((long) 319.19)
                    .build();

            Product product6 = Product.builder()
                    .productId(6L)
                    .label("BARRE COMPÉTITION FEMME")
                    .productTitle("BARRE COMPÉTITION FEMME")
                    .imageUrl("https://sport-equipement.shop/product/barre-competition-femme/")
                    .idCategory(3L)
                    .valid(true)
                    .description("La barre se caractérise par une adaptation aux tolérances de poids très strictes et un moletage prononcé adapté aux tentatives de type 1 répétition max.")
                    .price((long) 259.19)
                    .build();


            Product product7 = Product.builder()
                    .productId(7L)
                    .label("BARRE JUNIOR")
                    .productTitle("BARRE JUNIOR")
                    .imageUrl("https://sport-equipement.shop/product/bar-junior/")
                    .idCategory(3L)
                    .valid(true)
                    .description("La barre se caractérise par une adaptation aux tolérances de poids très strictes et un moletage prononcé adapté aux tentatives de type 1 répétition max.")
                    .price((long) 223.1)
                    .build();

            Product product8 = Product.builder()
                    .productId(8L)
                    .label("POWER BAG")
                    .productTitle("POWER BAG")
                    .imageUrl("https://sport-equipement.shop/product/power-bag/")
                    .idCategory(3L)
                    .valid(true)
                    .description("La barre se caractérise par une adaptation aux tolérances de poids très strictes et un moletage prononcé adapté aux tentatives de type 1 répétition max.")
                    .price((long) 45.59)
                    .build();

            Product product9 = Product.builder()
                    .productId(9L)
                    .label("ADJUSTABLE DUMBBELL")
                    .productTitle("ADJUSTABLE DUMBBELL")
                    .imageUrl("https://sport-equipement.shop/product/adjustable-dumbbell/")
                    .idCategory(3L)
                    .valid(true)
                    .description("Plus besoin d'acheter une quantité impressionnante d'haltères différents, fini : les haltères qui traînent partout... Avec leur design épuré et personnalisable sur demande associé à leur technologie novatrice, nos adjustable dumbbell offrent une alternative compacte aux sets d'haltères encombrants et peu pratiques. Notre adjustable dumbbell vous évite donc d'acquérir jusqu'à 24 paires d'haltères. Chaque haltère vous offre la possibilité de vous entraîner avec des poids allant de 2 à 24 kg ce qui vous fait de 15 niveaux d'ajustement. Ils sont également disponibles en 17 niveaux d'ajustement de 5 à 40 kg. Vous avez la possibilité d'acheter un seul haltère ou un set incluant deux haltères.")
                    .price((long) 199.19)
                    .build();

            Product product10 = Product.builder()
                    .productId(10L)
                    .label("HEX RUBBER DUMBBELL")
                    .productTitle("HEX RUBBER DUMBBELL")
                    .imageUrl("https://sport-equipement.shop/product/hex-rubber-dumbbell/")
                    .idCategory(3L)
                    .valid(true)
                    .description("Nos Hex rubber Dumbbell sont vendus par paires ainsi qu'à l'unité.\n\nCe modèle hexagonal en caoutchouc standard est une excellente option abordable qui continue d’offrir qualité, performance et longévité.\n\nLes extrémités robustes recouvertes de caoutchouc minimisent le bruit et limitent l’usure (à la fois des haltères et de votre sol), tandis que la poignée chromée est conçue de manière ergonomique pour offrir des sensations fermes, mais confortables quel que soit le type de prise.\n\n- Tête en caoutchouc ultra-résistante\n- Réduction du bruit, des dégâts au sol et de l’usure globale des dumbbells.\n- Poignée chromée ergonomique, facile à prendre en main\n\n1 kg : 7.19€ P.U/TTC\n2 kg : 13.19€ P.U/TTC\n2.5 kg : 16.79€ P.U/TTC\n3 kg : 20.39€ P.U/TTC\n4 kg : 26.39€ P.U/TTC\n5 kg : 32.39€ P.U/TTC\n6 kg : 38.39€ P.U/TTC\n7 kg : 45.39€ P.U/TTC\n7.5 kg : 49.19€ P.U/TTC\n8 kg : 51.59€ P.U/TTC\n9 kg : 58.79€ P.U/TTC\n10 kg : 64.79€ P.U/TTC\n12.5 kg : 80.39€ P.U/TTC\n15 kg : 97.19€ P.U/TTC\n17.5 kg : 112.79€ P.U/TTC\n20 kg : 128.39€ P.U/TTC\n22.5 kg : 145.19€ P.U/TTC\n25 kg :160.79€ P.U/TTC\n27.5 kg : 177.59€ P.U/TTC\n30 kg : 193.19€ P.U/TTC\n32.5 kg : 208.79€ P.U/TTC")
                    .price((long) 7.19)
                    .build();

            Product product11 = Product.builder()
                    .productId(11L)
                    .label("TRICEPS BAR")
                    .productTitle("TRICEPS BAR")
                    .imageUrl("https://sport-equipement.shop/product/182/")
                    .idCategory(3L)
                    .valid(true)
                    .description("Cette barre de musculation permet de développer la masse des biceps et triceps.\n\nGrip des poignées pour plus de stabilité et de sécurité.")
                    .price((long) 152.39)
                    .build();

            Product product12 = Product.builder()
                    .productId(12L)
                    .label("FITNESS BAR 1.5M")
                    .productTitle("FITNESS BAR 1.5M")
                    .imageUrl("https://sport-equipement.shop/product/fitness-bar-1-5m/")
                    .idCategory(3L)
                    .valid(true)
                    .description("Description à compléter.")
                    .price((long) 122.39)
                    .build();

            iProductService.save(product1);
            iProductService.save(product2);
            iProductService.save(product3);
            iProductService.save(product4);
            iProductService.save(product5);
            iProductService.save(product6);
            iProductService.save(product7);
            iProductService.save(product8);
            iProductService.save(product9);
            iProductService.save(product10);
            iProductService.save(product11);
            iProductService.save(product12);

            Category category = Category.builder()
                    .categoryId(1L)
                    .idParent(0L)
                    .label("MATÉRIEL DE MUSCULATION")
                    .categoryTitle("MATÉRIEL DE MUSCULATION")
                    .imageUrl("https://sport-equipement.shop/product-category/muscu/")
                    .valid(true)
                    .build();

            iCategoryService.save(category);

            Category category1 = Category.builder()
                    .categoryId(3L)
                    .idParent(1L)
                    .label("EQUIPEMENTS")
                    .categoryTitle("EQUIPEMENTS")
                    .imageUrl("https://sport-equipement.shop/product-category/muscu/equipements/")
                    .valid(true)
                    .build();

            iCategoryService.save(category1);

            Category category2 = Category.builder()
                    .categoryId(4L)
                    .idParent(1L)
                    .label("ACCESSOIRES DE MUSCULATION")
                    .categoryTitle("ACCESSOIRES DE MUSCULATION")
                    .imageUrl("https://sport-equipement.shop/product-category/muscu/freeweight/")
                    .valid(true)
                    .build();

            iCategoryService.save(category2);

            Category category4 = Category.builder()
                    .categoryId(5L)
                    .idParent(1L)
                    .label("ACCESSOIRES")
                    .categoryTitle("ACCESSOIRES")
                    .imageUrl("https://sport-equipement.shop/product-category/muscu/accessoires/")
                    .valid(true)
                    .build();

            iCategoryService.save(category4);

        };
    }


}
