import { FormGroup, ValidationErrors } from '@angular/forms';

export const sameValueGroupValidator = (
    field1Name: string,
    field2Name: string
) => {
    return (passwordsGroup: FormGroup): ValidationErrors | null => {
        const field1 = passwordsGroup.get(field1Name);
        const field2 = passwordsGroup.get(field2Name);

        if (!field1 || !field2) return null;

        if (field1.value !== field2.value) {
            field1.setErrors({
                ...field1.errors,
                sameValueGroupValidator: true,
            });
            field2.markAsTouched();
        } else if (field1.errors?.['sameValueGroupValidator']) {
            let { sameValueGroupValidator, ...errors } = field1.errors;

            if (Object.keys(errors).length) {
                field1.setErrors(errors);
            } else {
                field1.setErrors(null);
            }
        }

        field2.setErrors(field1.errors);

        if (field1.value === field2.value) return null;
        return { sameValueGroupValidator: true };
    };
};
