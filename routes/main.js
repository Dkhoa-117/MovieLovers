import express from "express";
const router = express.Router();
import { helloWorld } from "../controllers/hello-world";
import { login, register, dashboard } from "../controllers/private-route";
import auth from "../middlewares/authentication";

router.route("/hello-world").get(helloWorld);

router.route("/login").post(login);
router.route("/register").post(register);
// authentication route
router.route("/private").get(auth, dashboard);

export default router;
