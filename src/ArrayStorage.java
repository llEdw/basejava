import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int k;
    int count;
    Resume l;

    void clear() {
        for (int i = 0; i < k; i++) {
            storage[i] = null;
        }
        System.out.println("Массив очищен");
        k = 0;
    }

    void save(Resume r) {
        if (r.uuid == null) {
            System.out.println("Вы не ввели значение. Повторите попытку");
        } else {
            storage[k] = r;
            System.out.println(storage[k] + " добавлен(а) в массив");
            k++;
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < k; i++) {
            if (storage[i].uuid.equals(uuid)) {
                l = storage[i];
                count++;
                break;
            }
        }
        if (count == 0) {
            l = new Resume();
            l.uuid = "Такого резюме нет в массиве";
        }
        count = 0;
        return l;
    }

    void delete(String uuid) {
        for (int i = 0; i < k; i++) {
            if (storage[i].uuid.equals(uuid)) {
                for (int j = i; j < k - 1; j++) {
                    storage[j] = storage[j + 1];
                }
                k--;
                count++;
                System.out.println(uuid + " удален(а)");
                break;
            }
        }
        if (count == 0) {
            System.out.println("Такого резюме нет в массиве");
        }
        count = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, k);
    }

    int size() {
        return k;
    }
}
