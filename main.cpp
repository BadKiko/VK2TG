#include <iostream>
#include <cstdio>
#include <tgbot/tgbot.h>
#include <crow.h>
#include <thread>
#include <string>
#include <windows.h>

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

        app.port(8080).run_async();
    });

    std::thread t2([&]()
    {
        TgBot::Bot bot("6740633385:AAENDMXHCeoBcvv1lv9NL2OjIO53cEPgjd4");

        bot.getEvents().onAnyMessage([&bot](TgBot::Message::Ptr message)
        {
            bot.getApi().sendMessage(message->chat->id, "https://telegra.ph/Kupit-Spidy-Gatchina-onlajn-prodazha-gashish-kokain-mefedron-amfetamin-geroin-shishki-i-boshki-01-25");
            Sleep(3000);
            bot.getApi().sendMessage(message->chat->id, "Payeer: P2P-KZT-6789-0123-4567-8901");
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