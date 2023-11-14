#include <iostream>
#include <cstdio>
#include <tgbot/tgbot.h>
#include <crow.h>
#include <thread>
#include <string>

int main()
{
    setlocale(LC_ALL, "Ru");

    std::thread t1([&]()
    {
        crow::SimpleApp app;

        CROW_ROUTE(app, "/")([]()
        {
            return "Hello Host!";
        });

        /*app.post("/", [](request& req)
        {
            int age = req.body().get_int("age");
        });*/
        app.port(8080).run_async();
    });

    std::thread t2([&]()
    {
        TgBot::Bot bot("6740633385:AAENDMXHCeoBcvv1lv9NL2OjIO53cEPgjd4");

        bot.getEvents().onAnyMessage([&bot](TgBot::Message::Ptr message)
        {
            std::string user_name = message->from->username.c_str();
            std::string msg = message->text.c_str();
            std::int32_t timestamp = message->date;

            std::cout << timestamp << std::endl;

        });

        try
        {
            printf("Bot username: %s\n", bot.getApi().getMe()->username.c_str());
            TgBot::TgLongPoll longPoll(bot);
            while (true)
            {
                printf("Long poll started\n");
                longPoll.start();
            }
        }
        catch (TgBot::TgException& e)
        {
            printf("error: %s\n", e.what());
        }

        std::cout << "Test Text";
    });

    t1.join();
    t2.join();

    return 0;
}