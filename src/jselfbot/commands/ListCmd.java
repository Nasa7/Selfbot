/*
 * Copyright 2016 John Grosh (jagrosh).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jselfbot.commands;

import java.util.Collection;
import jselfbot.Command;
import jselfbot.entities.Emojis;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 *
 * @author John Grosh (jagrosh)
 */
public class ListCmd extends Command {
    private final Emojis emojis;
    public ListCmd(Emojis emojis)
    {
        this.emojis = emojis;
        this.name = "list";
        this.description = "lists custom emojis";
    }
    
    @Override
    protected void execute(String args, MessageReceivedEvent event) {
        Collection<String> list = emojis.getEmojiList();
        if(list.isEmpty())
        {
            tempReply("No custom emojis!", event);
            return;
        }
        StringBuilder sbuilder = new StringBuilder();
        list.stream().forEach(name -> sbuilder.append("\n`:").append(name).append(":` \u27A1 `").append(emojis.getEmoji(name)).append("`"));
        EmbedBuilder builder = new EmbedBuilder();
        if(event.getGuild()!=null)
            builder.setColor(event.getGuild().getSelfMember().getColor());
        builder.setAuthor("Custom Emojis:", null, null);
        builder.setDescription(sbuilder.toString().trim());
        reply(builder.build(), event);
    }
    
}
