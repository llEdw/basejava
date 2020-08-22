import java.util.Arrays;
/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int arraySize;

    void clear() {
        for (int i = 0; i < arraySize; i++) {
            storage[i] = null;
        }
        System.out.println("Массив очищен");
        arraySize = 0;
    }

    void save(Resume r) {
        if (r.uuid == null) {
            System.out.println("Вы не ввели значение. Повторите попытку");
        } else {
            storage[arraySize] = r;
            System.out.println(storage[arraySize] + " добавлен(а) в массив");
            arraySize++;
        }
    }

    Resume get(String uuid) {
        Resume resume = null;
        for (int i = 0; i < arraySize; i++) {
            if (storage[i].uuid.equals(uuid)) {
                resume = storage[i];
                break;
            }
        }
        return resume;
    }

    void delete(String uuid) {
        int foundResumes = 0;
        for (int i = 0; i < arraySize; i++) {
            if (storage[i].uuid.equals(uuid)) {
                for (int j = i; j < arraySize - 1; j++) {
                    storage[j] = storage[j + 1];
                }
                arraySize--;
                foundResumes++;
                System.out.println(uuid + " удален(а)");
                break;
            }
        }
        if (foundResumes == 0) {
            System.out.println("Такого резюме нет в массиве");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, arraySize);
    }

    int size() {
        return arraySize;
    }
}
