import express from "express";
const router = express.Router();

import movies from "../controllers/movies.js";

router.route("/all").get(movies.getAll);

export default router;
