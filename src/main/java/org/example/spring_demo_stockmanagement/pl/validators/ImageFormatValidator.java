package org.example.spring_demo_stockmanagement.pl.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class ImageFormatValidator implements ConstraintValidator<ImageFormat, MultipartFile> {
    @Override
    public void initialize(ImageFormat constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        if(value == null){
            return true;
        }
        String imageName = value.getOriginalFilename();
        return imageName.endsWith(".jpg") || imageName.endsWith(".jpeg") ||imageName.endsWith(".png");
    }
}
