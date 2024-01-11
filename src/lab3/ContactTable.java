package lab3;

import java.util.LinkedList;

public class ContactTable {
    private LinkedList<Contact>[] table;

    public ContactTable() {
        this(10);
    }

    public ContactTable(int initialCapacity) {
        table = new LinkedList[initialCapacity];
    }


    public void addContact(Contact contact) {
        int index = hash(contact.getPhoneNumber());
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }

        table[index].add(contact);
    }

    public Contact findContact(String phoneNumber) {
        int index = hash(phoneNumber);
        if (table[index] != null) {
            for (Contact contact : table[index]) {
                if (contact.getPhoneNumber().equals(phoneNumber)) {
                    return contact;
                }
            }
        }
        return null;
    }

    public void removeContact(String phoneNumber) {
        int index = hash(phoneNumber);
        if (table[index] != null) {
            LinkedList<Contact> list = table[index];
            for (Contact contact : list) {
                if (contact.getPhoneNumber().equals(phoneNumber)) {
                    list.remove(contact);
                    return;
                }
            }
        }
    }

    private int hash(String phoneNumber) {
        int index = phoneNumber.hashCode() % table.length;
        return (index < 0) ? index + table.length : index;
    }


    public static void main(String[] args) {
        ContactTable contactTable = new ContactTable();
        // Добавление контактов
        Contact contact1 = new Contact("89020665454", "Стасюк Слава", "Slava@mail.ru", "@Slav");
        Contact contact2 = new Contact("+79033334436", "Славюк Стас", "Stas@mail.ru", "@Stas");
        contactTable.addContact(contact1);
        contactTable.addContact(contact2);
        // Поиск контакта
        Contact foundContact = contactTable.findContact("89020665454");
        if (foundContact != null) {
            System.out.println("Имя: " + foundContact.getName());
        } else {
            System.out.println("Контакт не найден.");
        }
        // Удаление контакта
        contactTable.removeContact("89020665454");
        // Повторный поиск удаленного контакта
        System.out.println(contactTable.findContact("89020665454"));
    }
}
