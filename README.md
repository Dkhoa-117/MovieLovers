# Deploy app on Heroku

- create a project and clone an empty heroku git project.
- install package with this command.

```
npm i -D @sveltejs/adapter-node@next
```

- add api key into Config Vars setting on heroku.
- add start script into package.json file.

```
"start": "node build/index.js",
```

- add Buildpacks (heroku/nodejs) on Heroku setting.
- Check your application, if it's an error then check Heroku logs with this command.

```
heroku logs
```
