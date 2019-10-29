package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.File;

import javafx.scene.image.Image;

/**
 *  Represents a Person's photo in the address book.
 *  Guarantees: immutable; is valid as declared in {@link #isValidFilePath(String)}
 */
public class Photo {


    public static final String MESSAGE_CONSTRAINTS = "Image filepath should be of the format local-part.png "
            + "and adhere to the following constraints:\n"
            + "1. The local-part should only contain alphanumeric characters and underscores.\n"
            + "2. This is followed by a '.' and only the image extension 'png' is allowed. ";
    public static final String VALIDATION_REGEX = "^[\\w]+(\\.(?i)(png))$";
    private static final String IMAGE_DIRECTORY = "images/";
    public final Image photo;
    public final String value;
    private final File imageFile;

    public Photo() {
        value = "default.png";
        imageFile = new File(IMAGE_DIRECTORY + value);
        photo = new Image("file://" + imageFile.toURI().getPath());
    }

    public Photo(String image) {
        requireNonNull(image);
        checkArgument(isValidFilePath(image), MESSAGE_CONSTRAINTS);
        value = image;
        imageFile = new File(IMAGE_DIRECTORY + value);
        photo = new Image("file://" + imageFile.toURI().getPath());
    }

    public static boolean isValidFilePath(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String toString() {
        return value;
    }
}
