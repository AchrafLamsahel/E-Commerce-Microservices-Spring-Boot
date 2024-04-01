package org.cataloguemicroservice.dtos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ThreeCategory {
    private List<CategoriesTree> categoriesTrees = new ArrayList<>();


}
