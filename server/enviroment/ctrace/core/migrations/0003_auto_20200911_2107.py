# Generated by Django 3.1.1 on 2020-09-11 21:07

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('core', '0002_interaction_profile'),
    ]

    operations = [
        migrations.AlterField(
            model_name='profile',
            name='infection_date',
            field=models.DateField(blank=True),
        ),
    ]
