import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                storage[i] = null;
            } else break;
        }
    }

    void save(Resume r) {
        if (storage[storage.length - 1] == null) {
            for (int i = 0; i < storage.length - 1; i++) {
                if (storage[i] == null) {
                    storage[i] = r;
                    break;
                }
            }
        } else try {
            throw new Exception("В массиве нет свободного места");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Resume get(String uuid) {
        for (Resume r : storage) {
            if (r != null) {
                if (r.uuid.equals(uuid)) {
                    return r;
                }
            }
        }
        return null;
    }

    void delete(String uuid) {
        int delIndex = -1;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].uuid.equals(uuid)) {
                delIndex = i;
                break;
            }
        }
        if (delIndex != -1) {
            for (int i = delIndex; i < storage.length - 1; i++) {
                storage[i] = storage[i + 1];
            }
            storage[storage.length - 1] = null;
        } else try {
            throw new Exception("В массиве отсутствует данный элемент");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.stream(storage).filter(Objects::nonNull).toArray(Resume[]::new);
    }

    int size() {
        return storage.length;
    }
}
