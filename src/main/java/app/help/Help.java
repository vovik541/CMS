package app.help;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Help {
    public static void main(String[] args) throws IOException {

        Properties properties=new Properties();

        FileOutputStream messages=new FileOutputStream("src/main/resources/messages.properties");

        properties.setProperty("greetings","We are glad to see you!");
        properties.setProperty("register","Register");
        properties.setProperty("signOut","Sign Out");
        properties.setProperty("signIn","Sign In");
        properties.setProperty("signUp","Sign Up");
        properties.setProperty("submit","Submit");
        properties.setProperty("delete","Delete");
        properties.setProperty("confirm","Confirm");
        properties.setProperty("refuse","Refuse");
        properties.setProperty("status","Status:");
        properties.setProperty("accepted","ACCEPTED");
        properties.setProperty("notAccepted","NOT ACCEPTED");
        properties.setProperty("show","Show");
        properties.setProperty("yes","Yes");
        properties.setProperty("no","No");
        properties.setProperty("userId","User ID:");
        properties.setProperty("firstName","First Name:");
        properties.setProperty("lastName","Last Name");
        properties.setProperty("login","Login:");
        properties.setProperty("email","E-mail:");
        properties.setProperty("role","Role:");
        properties.setProperty("next","Next");
        properties.setProperty("previous","Previous");
        properties.setProperty("id","ID:");
        properties.setProperty("sent","Sent");
        properties.setProperty("speakerAcc","Speaker accepted");
        properties.setProperty("speakerDidntAcc","Speaker didn't accept");
        properties.setProperty("speakerAgreement","Speaker Agreement");
        properties.setProperty("giveAgreement","Give Agreement");
        properties.setProperty("currentConferences","Current conferences:");
        properties.setProperty("accept","Accept");
        properties.setProperty("deny","Deny");
        properties.setProperty("pastConferences","Past Conferences:");
        properties.setProperty("speakerId","Speaker ID:");
        properties.setProperty("conferenceId","Conference ID:");
        properties.setProperty("subject","Subject:");
        properties.setProperty("message","Message:");
        properties.setProperty("userInputErrorMessage","Fields mustn't be empty!");
        properties.setProperty("userExistsError","User with such log or email exists!");
        properties.setProperty("signOutMessage","You were logged out");

        properties.setProperty("user.registerInConference","Register in conference");
        properties.setProperty("user.visited","Conferences you have visited");
        properties.setProperty("user.registered","You are registered.");
        properties.setProperty("user.giveARate","Give a rate.");
        properties.setProperty("user.rate","Rate:");
        properties.setProperty("user.haveRated","You have rated a speaker:");

        properties.setProperty("login.enter","Sign in:");
        properties.setProperty("login.login","Login");
        properties.setProperty("login.password","Password");
        properties.setProperty("login.errorLoginPassMessage","Login or Password doesn't suit!");

        properties.setProperty("speaker","Speaker:");
        properties.setProperty("speaker.offer","Offer a Speech");
        properties.setProperty("speaker.confName","Topic / Theme:");
        properties.setProperty("speaker.beginsAt","Begins at:");
        properties.setProperty("speaker.endsAt", "Ends at:");
        properties.setProperty("speaker.location", "Location:");
        properties.setProperty("speaker.date","Date:");
        properties.setProperty("speaker.confAdded","The Conference has been added.");
        properties.setProperty("speaker.incorrectInput","Incorrect input! Please, check all fields and try again.");
        properties.setProperty("speaker.moreDetails","More details:");
        properties.setProperty("speaker.accByModer","Accepted by moder:");
        properties.setProperty("speaker.present","Present:");
        properties.setProperty("speaker.registered","Registered:");
        properties.setProperty("speaker.denyMessage","You can't delete conf accepted by Moder");

        properties.setProperty("moder.accBySpeaker","Accepted by speaker:");
        properties.setProperty("moder.selectRecRepPage","Select records per page:");
        properties.setProperty("moder.giveSpeakerRole","Give Role:");
        properties.setProperty("moder.makeSpeaker","Make Speaker");
        properties.setProperty("moder.makeUser","Make User");
        properties.setProperty("moder.noPermission","You have po permission to change this role");
        properties.setProperty("moder.letterText","Write a letter to the Conference members:");
        properties.setProperty("moder.changeTime","Change Time:");
        properties.setProperty("moder.changeSpeaker","Change Speaker:");
        properties.setProperty("moder.changeLocation","Change Location:");
        properties.setProperty("moder.changeConferenceName","Change Conference Name:");

        properties.store(messages,"");

        //------------------------------------------------------------------------------

        FileOutputStream messages_en=new FileOutputStream("src/main/resources/messages_en.properties");

        properties.setProperty("greetings","We are glad to see you!");
        properties.setProperty("register","Register");
        properties.setProperty("signOut","Sign Out");
        properties.setProperty("signIn","Sign In");
        properties.setProperty("signUp","Sign Up");
        properties.setProperty("submit","Submit");
        properties.setProperty("delete","Delete");
        properties.setProperty("confirm","Confirm");
        properties.setProperty("refuse","Refuse");
        properties.setProperty("status","Status:");
        properties.setProperty("accepted","ACCEPTED");
        properties.setProperty("notAccepted","NOT ACCEPTED");
        properties.setProperty("show","Show");
        properties.setProperty("yes","Yes");
        properties.setProperty("no","No");
        properties.setProperty("userId","User ID:");
        properties.setProperty("firstName","First Name:");
        properties.setProperty("lastName","Last Name");
        properties.setProperty("login","Login:");
        properties.setProperty("email","E-mail:");
        properties.setProperty("role","Role:");
        properties.setProperty("next","Next");
        properties.setProperty("previous","Previous");
        properties.setProperty("id","ID:");
        properties.setProperty("sent","Sent");
        properties.setProperty("speakerAcc","Speaker accepted");
        properties.setProperty("speakerDidntAcc","Speaker didn't accept");
        properties.setProperty("speakerAgreement","Speaker Agreement");
        properties.setProperty("giveAgreement","Give Agreement");
        properties.setProperty("currentConferences","Current conferences:");
        properties.setProperty("accept","Accept");
        properties.setProperty("deny","Deny");
        properties.setProperty("pastConferences","Past Conferences:");
        properties.setProperty("speakerId","Speaker ID:");
        properties.setProperty("conferenceId","Conference ID:");
        properties.setProperty("subject","Subject:");
        properties.setProperty("message","Message:");
        properties.setProperty("userInputErrorMessage","Fields mustn't be empty!");
        properties.setProperty("userExistsError","User with such log or email exists!");
        properties.setProperty("signOutMessage","You were logged out");

        properties.setProperty("user.registerInConference","Register in conference");
        properties.setProperty("user.visited","Conferences you have visited");
        properties.setProperty("user.registered","You are registered.");
        properties.setProperty("user.giveARate","Give a rate.");
        properties.setProperty("user.rate","Rate:");
        properties.setProperty("user.haveRated","You have rated a speaker:");

        properties.setProperty("login.enter","Sign in:");
        properties.setProperty("login.login","Login");
        properties.setProperty("login.password","Password");
        properties.setProperty("login.errorLoginPassMessage","Login or Password doesn't suit!");

        properties.setProperty("speaker","Speaker:");
        properties.setProperty("speaker.offer","Offer a Speech");
        properties.setProperty("speaker.confName","Topic / Theme:");
        properties.setProperty("speaker.beginsAt","Begins at:");
        properties.setProperty("speaker.endsAt", "Ends at:");
        properties.setProperty("speaker.location", "Location:");
        properties.setProperty("speaker.date","Date:");
        properties.setProperty("speaker.confAdded","The Conference has been added.");
        properties.setProperty("speaker.incorrectInput","Incorrect input! Please, check all fields and try again.");
        properties.setProperty("speaker.moreDetails","More details:");
        properties.setProperty("speaker.accByModer","Accepted by moder:");
        properties.setProperty("speaker.present","Present:");
        properties.setProperty("speaker.registered","Registered:");
        properties.setProperty("speaker.denyMessage","You can't delete conf accepted by Moder");

        properties.setProperty("moder.accBySpeaker","Accepted by speaker:");
        properties.setProperty("moder.selectRecRepPage","Select records per page:");
        properties.setProperty("moder.giveSpeakerRole","Give Role:");
        properties.setProperty("moder.makeSpeaker","Make Speaker");
        properties.setProperty("moder.makeUser","Make User");
        properties.setProperty("moder.noPermission","You have po permission to change this role");
        properties.setProperty("moder.letterText","Write a letter to the Conference members:");
        properties.setProperty("moder.changeTime","Change Time:");
        properties.setProperty("moder.changeSpeaker","Change Speaker:");
        properties.setProperty("moder.changeLocation","Change Location:");
        properties.setProperty("moder.changeConferenceName","Change Conference Name:");

        properties.store(messages_en,"");

        //------------------------------------------------------------------------------

        FileOutputStream messages_ua=new FileOutputStream("src/main/resources/messages_ua.properties");

        properties.setProperty("greetings","Ми раді вас бачити!");
        properties.setProperty("register","Зареєструватися");
        properties.setProperty("signOut","Вихід");
        properties.setProperty("signIn","Вхід");
        properties.setProperty("signUp","Зареєструватися");
        properties.setProperty("submit","Підтвердити");
        properties.setProperty("delete","Удалити");
        properties.setProperty("confirm","Розрішити");
        properties.setProperty("refuse","Відказати");
        properties.setProperty("status","Статус:");
        properties.setProperty("accepted","ПІДТВЕРДЖЕНО");
        properties.setProperty("notAccepted","НЕ ПІДТВЕРДЖЕНО");
        properties.setProperty("show","Показати");
        properties.setProperty("yes","Так");
        properties.setProperty("no","Ні");
        properties.setProperty("userId","ID Користувача:");
        properties.setProperty("firstName","Ім'я:");
        properties.setProperty("lastName","Фамілія");
        properties.setProperty("login","Логін:");
        properties.setProperty("email","E-mail:");
        properties.setProperty("role","Роль:");
        properties.setProperty("next","Наступний");
        properties.setProperty("previous","Попередній");
        properties.setProperty("id","ID:");
        properties.setProperty("sent","Відправити");
        properties.setProperty("speakerAcc","Спікер погодився");
        properties.setProperty("speakerDidntAcc","Спікер не погодився");
        properties.setProperty("speakerAgreement","Стосовно спікера");
        properties.setProperty("giveAgreement","Погодитись");
        properties.setProperty("currentConferences","Майбутні конференції:");
        properties.setProperty("accept","Підтвердити");
        properties.setProperty("deny","Відмовитись");
        properties.setProperty("pastConferences","Минулі конференції:");
        properties.setProperty("speakerId","ID Спікера:");
        properties.setProperty("conferenceId","ID Конференції:");
        properties.setProperty("subject","Тема:");
        properties.setProperty("message","Повідомлення:");
        properties.setProperty("userInputErrorMessage","Будь ласка, заповніть всі поля!");
        properties.setProperty("userExistsError","Користувач із таким логіном або поштою вже існує!");
        properties.setProperty("signOutMessage","Ви вийшли з системи");

        properties.setProperty("user.registerInConference","Зареєструватися в Конференції");
        properties.setProperty("user.visited","Ваші відвідані конференції");
        properties.setProperty("user.registered","Ви зареєстровані.");
        properties.setProperty("user.giveARate","Дати рейтинг.");
        properties.setProperty("user.rate","Рейтинг:");
        properties.setProperty("user.haveRated","Ви оцінили спікера на:");

        properties.setProperty("login.enter","Вхід:");
        properties.setProperty("login.login","Логін");
        properties.setProperty("login.password","Пароль");
        properties.setProperty("login.errorLoginPassMessage","Пароль чи Логін не підходять!");

        properties.setProperty("speaker","Спікер:");
        properties.setProperty("speaker.offer","Запропонувати Конференцію");
        properties.setProperty("speaker.confName","Тема/Назва:");
        properties.setProperty("speaker.beginsAt","Початок о:");
        properties.setProperty("speaker.endsAt", "Кінець о:");
        properties.setProperty("speaker.location", "Місце проведення:");
        properties.setProperty("speaker.date","Дата:");
        properties.setProperty("speaker.confAdded","Конференція щойно була додана.");
        properties.setProperty("speaker.incorrectInput","Неправильно введені данні! Будь ласка, заповніть всі поля та спробуйте ще раз.");
        properties.setProperty("speaker.moreDetails","Більше деталей:");
        properties.setProperty("speaker.accByModer","Підтверджено Модератором:");
        properties.setProperty("speaker.present","Присутньо");
        properties.setProperty("speaker.registered","Зареєстровано");
        properties.setProperty("speaker.denyMessage","Ви не можете удалити Конференцію, підтверджену Модератором");

        properties.setProperty("moder.accBySpeaker","Підтверджено Спікером:");
        properties.setProperty("moder.selectRecRepPage","Встановити кількість записів на сторінку:");
        properties.setProperty("moder.giveSpeakerRole","Дати Роль:");
        properties.setProperty("moder.makeSpeaker","Зробити Спікером");
        properties.setProperty("moder.makeUser","Зробити Користувачем");
        properties.setProperty("moder.noPermission","У вас недостатній рівень доступу");
        properties.setProperty("moder.letterText","Написати листа Учасникам Конференції:");
        properties.setProperty("moder.changeTime","Змінити час:");
        properties.setProperty("moder.changeSpeaker","Змінити спікера:");
        properties.setProperty("moder.changeLocation","Змінити місце проведення:");
        properties.setProperty("moder.changeConferenceName","Змінити Тему/Назву Конференції:");

        properties.store(messages_ua,"");

        //------------------------------------------------------------------------------

        FileOutputStream messages_ru=new FileOutputStream("src/main/resources/messages_ru.properties");

        properties.setProperty("greetings","Мы рады вас видеть!");
        properties.setProperty("register","Регистрация");
        properties.setProperty("signOut","Выход");
        properties.setProperty("signIn","Вход");
        properties.setProperty("signUp","Зарегистрироваться");
        properties.setProperty("submit","Подтвердить");
        properties.setProperty("delete","Удалить");
        properties.setProperty("confirm","Подтвердить");
        properties.setProperty("refuse","Отменить");
        properties.setProperty("status","Статус:");
        properties.setProperty("accepted","ОДОБРЕНО");
        properties.setProperty("notAccepted","НЕ ОДОБРЕНО");
        properties.setProperty("show","Показать");
        properties.setProperty("yes","Да");
        properties.setProperty("no","Нет");
        properties.setProperty("userId","ID Пользователя:");
        properties.setProperty("firstName","Имя:");
        properties.setProperty("lastName","Фамилия");
        properties.setProperty("login","Логин:");
        properties.setProperty("email","Почта:");
        properties.setProperty("role","Роль:");
        properties.setProperty("next","Следующий");
        properties.setProperty("previous","Предыдущий");
        properties.setProperty("id","ID:");
        properties.setProperty("sent","Отправить");
        properties.setProperty("speakerAcc","Спикер согласился");
        properties.setProperty("speakerDidntAcc","Спикер не согласился");
        properties.setProperty("speakerAgreement","Соглашение Спикера");
        properties.setProperty("giveAgreement","Разрешить");
        properties.setProperty("currentConferences","Текущие конференции:");
        properties.setProperty("accept","Подтвердить");
        properties.setProperty("deny","Отказать");
        properties.setProperty("pastConferences","Прошлые конференции:");
        properties.setProperty("speakerId","ID Спикера:");
        properties.setProperty("conferenceId","ID конференции:");
        properties.setProperty("subject","Тема:");
        properties.setProperty("message","Сообщение:");
        properties.setProperty("userInputErrorMessage","Поля не должны быть пустыми!");
        properties.setProperty("userExistsError","Пользователь с таким логином или почтой существует!");
        properties.setProperty("signOutMessage","Вы вышли из системы");

        properties.setProperty("user.registerInConference","Принять учястие в конференции");
        properties.setProperty("user.visited","Конференции, на которых вы побывали");
        properties.setProperty("user.registered","Вы зарегистрированы.");
        properties.setProperty("user.giveARate","Дайте оценку.");
        properties.setProperty("user.rate","Рейтинг:");
        properties.setProperty("user.haveRated","Вы оценили спикера на:");

        properties.setProperty("login.enter","Вход:");
        properties.setProperty("login.login","Логин");
        properties.setProperty("login.password","Пароль");
        properties.setProperty("login.errorLoginPassMessage","Не подходит Логин или Пароль");

        properties.setProperty("speaker","Спикер:");
        properties.setProperty("speaker.offer","Предложить доклад");
        properties.setProperty("speaker.confName","Тема/Название:");
        properties.setProperty("speaker.beginsAt","Начало в:");
        properties.setProperty("speaker.endsAt", "Конец в:");
        properties.setProperty("speaker.location", "Место проведения:");
        properties.setProperty("speaker.date","Дата:");
        properties.setProperty("speaker.confAdded","Доклад был додан.");
        properties.setProperty("speaker.incorrectInput","Некорректный ввод данных. Пожалуйста, заполните все поля и повторите сного.");
        properties.setProperty("speaker.moreDetails","Подробнее:");
        properties.setProperty("speaker.accByModer","Разрешено Модератором:");
        properties.setProperty("speaker.present","Присутствовало:");
        properties.setProperty("speaker.registered","Зарегистрировано:");
        properties.setProperty("speaker.denyMessage","Вы не можете удалить конференцию, которую подтвердил Модератор.");

        properties.setProperty("moder.accBySpeaker","Подтверждено Спикером:");
        properties.setProperty("moder.selectRecRepPage","Установить количество записей на страницу:");
        properties.setProperty("moder.giveSpeakerRole","Установить Роль:");
        properties.setProperty("moder.makeSpeaker","Сделать Спикером");
        properties.setProperty("moder.makeUser","Сделать Пользоватилем");
        properties.setProperty("moder.noPermission","У вас недостаточно прав");
        properties.setProperty("moder.letterText","Напишите лист для участников Доклада:");
        properties.setProperty("moder.changeTime","Изменить время:");
        properties.setProperty("moder.changeSpeaker","Изменить Спикера:");
        properties.setProperty("moder.changeLocation","Изменить место проведения:");
        properties.setProperty("moder.changeConferenceName","Изменить тему доклада:");

        properties.store(messages_ru,"");
    }
}