#include <iostream>
#include <cstdio>
#include <tgbot/tgbot.h>

int main()
{
    setlocale(LC_ALL, "Ru");
    TgBot::Bot bot("6740633385:AAENDMXHCeoBcvv1lv9NL2OjIO53cEPgjd4");
    bot.getEvents().onCommand("start", [&bot](TgBot::Message::Ptr message)
    {
        bot.getApi().sendMessage(message->chat->id, "Hi, Penis!");
    });
    bot.getEvents().onCommand("suka", [&bot](TgBot::Message::Ptr message)
    {
        bot.getApi().sendMessage(message->chat->id, "Hahahaah! Such my cock, beach)))");
    });
    bot.getEvents().onAnyMessage([&bot](TgBot::Message::Ptr message)
    {
        printf("User wrote %s, ID: \n", message->text.c_str());
        if (StringTools::startsWith(message->text, "/start"))
        {
            return;
        }
        bot.getApi().sendMessage(message->chat->id, "Your message is: " + message->text);
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
    return 0;
}