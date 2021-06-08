package com.example.quiz_game_project.data;

import com.example.quiz_game_project.data.remote.questions.models.Category;

public class CategoryRepository {

    private Category[] data;

    private static CategoryRepository instance;

    public static CategoryRepository getInstance() {
        if(instance == null) instance = new CategoryRepository();
        return instance;
    }

    private CategoryRepository() {
        this.data = new Category[]{};
    }

    public void saveCategories(Category[] data) {
        this.data = data;
    }

    public String[] getData() {
        String [] array = new String[data.length];
        for (int i = 0; i <data.length; i++) {
            array[i] = data[i].name;
        }
        return array;
    }

    public Category[] getCategories(){
        return data;
    }

    public int findCategoryIdByName(String name){
        int categoryId = 0;
        for(int i = 0; i < data.length; i++) {
            if (data[i].name.equals(name)) {
                categoryId = data[i].id;
            }
        }
        return categoryId;
    }
}
