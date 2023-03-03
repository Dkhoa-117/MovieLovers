import express from "express";
const router = express.Router();

import users from "../controllers/users.js";

router.route("/all").get(users.getAll);

export default router;
