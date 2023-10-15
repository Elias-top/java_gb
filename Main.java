import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Main {
  static String[] company = new String[]{"DELL", "Samsung", "Lenovo", "LG"};
  static int[] ram_gb = new int[]{8, 16, 32, 64};
  static String[] cpu = new String[]{"Intel i5", "Ryzen 5600x", "Pentium", "FX-8150"};
  static String[] gpu = new String[]{"RTX3060", "Vega56", "GTX1080", "RTX2080S"};

  static HashMap<Integer, String> filtr_map = new HashMap<>();

  public static void main(String[] args) 
  {
    HashSet<Laptop> myLaptops = new HashSet<Laptop>();

    get_random_laptops(myLaptops, 10);
    printSet(myLaptops);
    while(true)
    {
        main_cycle(myLaptops);
    }

  }

  static void get_random_laptops(HashSet<Laptop> myLaptops, int num_of_laptops)
  {
    Random random = new Random();

    for(int i = 0; i < num_of_laptops; i++)
    {
      Laptop lap_new = new Laptop();
      lap_new.company = company[random.nextInt(company.length)];
      lap_new.gpu = gpu[random.nextInt(gpu.length)];
      lap_new.cpu = cpu[random.nextInt(cpu.length)];
      lap_new.ram_gb = ram_gb[random.nextInt(ram_gb.length)];
      myLaptops.add(lap_new);
    }
  }
  static void printSet(HashSet<Laptop> myLaptops)
  {
    for(Laptop lap : myLaptops)
    {
      System.out.println(lap);
    }
  }

  static void main_cycle(HashSet<Laptop> myLaptops)
  {
    Scanner console = new Scanner(System.in);

    System.out.println("Выберите фильтр:");
    System.out.println("1. Компания:");
    System.out.println("2. ОЗУ:");
    System.out.println("3. Видеокарта:");
    System.out.println("4. Процессор:");
    System.out.println("5. Поиск:");
    System.out.println("6. Очистить все фильтры:");

    int number_filt = console.nextInt();
    int i = 0;
    if(number_filt == 1)
    {
      System.out.println("Выберите компанию из следующих:");
      for(String str : company)
      {
        i++;
        System.out.println(i + ". " + str);
      }

      int user_number = console.nextInt();
      AddFiltrParam(number_filt, company[user_number - 1]);
    }

    if(number_filt == 2)
    {
      System.out.println("Напишите минимальное количество ОЗУ:");

      int user_ram_input = console.nextInt();
      AddFiltrParam(number_filt, Integer.toString(user_ram_input));
    }

    if(number_filt == 3)
    {
      System.out.println("Выберите видеокарту из списка:");
      for(String str : gpu)
      {
        i++;
        System.out.println(i + ". " + str);
      }

      int user_number = console.nextInt();
      AddFiltrParam(number_filt, gpu[user_number - 1]);
    }

    if(number_filt == 4)
    {
      System.out.println("Выберите процессор из списка:");
      for(String str : cpu)
      {
        i++;
        System.out.println(i + ". " + str);
      }

      int user_number = console.nextInt();
      AddFiltrParam(number_filt, cpu[user_number - 1]);
    }

    if(number_filt == 5)
    { 
      System.out.println("По выбранным фильтрам " + filtr_map.toString() +" найдены следующие ноутбуки:");
      printSet(findByParam(myLaptops));
    }

    if(number_filt == 6)
    { 
      RemoveAllFiltr();
    }
  }

  static void RemoveAllFiltr()
  {
    for(int num_filt = 0; num_filt < 5; num_filt++)
    {
      filtr_map.remove(num_filt);
    }
    System.out.println("Фильтры очищены!");
  }

  static void AddFiltrParam(int num_filt, String new_param)
  {
    System.out.println("Добавлен фильтр c параметром: " + new_param);
    filtr_map.put(num_filt, new_param);
  }

  static HashSet<Laptop> findByParam(HashSet<Laptop> laps_user)
  {
      HashSet<Laptop> laps_find = new HashSet<>();
      String name_comp;
      int cout_ram;
      String name_cpu;
      String name_gpu;
      //System.out.println(filtr_map.toString());
      for(Laptop lap : laps_user)
      {
      if(filtr_map.containsKey(1))
        name_comp = filtr_map.get(1);
      else
        name_comp = lap.company;

      if(filtr_map.containsKey(2))
        cout_ram = Integer.parseInt(filtr_map.get(2));
      else
        cout_ram = lap.ram_gb;   

      if(filtr_map.containsKey(3))
        name_gpu = filtr_map.get(3);
      else
        name_gpu = lap.gpu;    

      if(filtr_map.containsKey(4))
        name_cpu = filtr_map.get(4);
      else
      name_cpu = lap.cpu;   

      if(lap.ram_gb >= cout_ram && lap.company == name_comp && lap.cpu == name_cpu && lap.gpu == name_gpu)
      {
        laps_find.add(lap);
      }
      }
      return laps_find;
  }
}